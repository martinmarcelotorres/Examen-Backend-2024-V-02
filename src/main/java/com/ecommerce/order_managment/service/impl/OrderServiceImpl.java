package com.ecommerce.order_managment.service.impl;


import com.ecommerce.order_managment.domain.dto.OrderRequestDto;
import com.ecommerce.order_managment.domain.dto.OrderResponseDto;
import com.ecommerce.order_managment.domain.mapper.OrderMapper;
import com.ecommerce.order_managment.domain.model.Order;
import com.ecommerce.order_managment.domain.model.OrderItem;
import com.ecommerce.order_managment.exception.ResourceNotFoundException;
import com.ecommerce.order_managment.repository.OrderRepository;
import com.ecommerce.order_managment.repository.ProductsRepository;
import com.ecommerce.order_managment.repository.UsersRepository;
import com.ecommerce.order_managment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;

    @Override
    public Mono<OrderResponseDto> findById(String _id) {
        return orderRepository.findById(_id)
                .map(OrderMapper::toDto)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Order not found with id " + _id)));
    }

    @Override
    public Flux<OrderResponseDto> findAll() {
        return orderRepository.findAll()
                .map(OrderMapper::toDto);
    }

    @Override
    public Flux<OrderResponseDto> findPending() {
        return orderRepository.findAll()
                .filter(order -> "P".equals(order.getStatus()))
                .map(OrderMapper::toDto);
    }

    @Override
    public Flux<OrderResponseDto> findConfirm() {
        return orderRepository.findAll()
                .filter(order -> "C".equals(order.getStatus()))
                .map(OrderMapper::toDto);
    }

    @Override
    public Mono<OrderResponseDto> create(OrderRequestDto request) {
        return orderRepository.save(OrderMapper.toModel(request))
                .map(OrderMapper::toDto);
    }

    @Override
    public Mono<OrderResponseDto> updateOrder(String _id, OrderRequestDto requestDto) {
        return orderRepository.findById(_id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Order not found with id " + _id)))
                .flatMap(order -> {
                    return usersRepository.findById(requestDto.getClientId())
                            .switchIfEmpty(Mono.error(new IllegalArgumentException("User with the given ID does not exist.")))
                            .flatMap(user -> {
                                if (!"CL".equals(user.getType()) || !"A".equals(user.getStatus())) {
                                    return Mono.error(new IllegalArgumentException("User is not authorized to make orders."));
                                }

                                List<OrderItem> items = requestDto.getItems();
                                return Flux.fromIterable(items)
                                        .flatMap(item -> productsRepository.findById(item.getProductId())
                                                .switchIfEmpty(Mono.error(new IllegalArgumentException("Product with ID " + item.getProductId() + " does not exist.")))
                                                .flatMap(product -> {
                                                    if (!"A".equals(product.getStatus()) || product.getStock() <= 0) {
                                                        return Mono.error(new IllegalArgumentException("Product with ID " + product.getId() + " is not available."));
                                                    }
                                                    if (item.getQuantity() > product.getStock()) {
                                                        return Mono.error(new IllegalArgumentException("Not enough stock available for product with ID " + product.getId()));
                                                    }
                                                    return Mono.just(item);
                                                }))
                                        .collectList()
                                        .flatMap(orderItems -> {
                                            // Actualiza los campos generales del pedido
                                            order.setClientId(requestDto.getClientId());
                                            order.setTotal(requestDto.getTotal());
                                            order.setStatus(requestDto.getStatus());
                                            order.setOrderDate(requestDto.getOrderDate());
                                            order.setItems(orderItems);

                                            // Calcula el total del pedido sumando los subtotales de todos los productos
                                            double total = orderItems.stream().mapToDouble(OrderItem::getSubtotal).sum();
                                            order.setTotal(total);

                                            // Actualiza el stock de los productos
                                            return Flux.fromIterable(orderItems)
                                                    .flatMap(item -> productsRepository.findById(item.getProductId())
                                                            .flatMap(product -> {
                                                                product.setStock(product.getStock() - item.getQuantity());
                                                                return productsRepository.save(product);
                                                            }))
                                                    .then(orderRepository.save(order));
                                        });
                            });
                })
                .map(OrderMapper::toDto);
    }

    @Override
    public Mono<Void> confirmOrder(String _id) {
        return orderRepository.findById(_id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Order not found with id " + _id)))
                .flatMap(order -> {
                    order.setStatus("C");
                    return orderRepository.save(order);
                })
                .doOnSuccess(unused -> log.info("Order confirmed with id: {}", _id))
                .then();
    }

    @Override
    public Mono<Order> createOrder(OrderRequestDto orderRequestDto) {
        return usersRepository.findById(orderRequestDto.getClientId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User with the given ID does not exist.")))
                .flatMap(user -> {
                    if (!"CL".equals(user.getType()) || !"A".equals(user.getStatus())) {
                        return Mono.error(new IllegalArgumentException("User is not authorized to make orders."));
                    }
                    List<OrderItem> items = orderRequestDto.getItems();
                    return Flux.fromIterable(items)
                            .flatMap(item -> productsRepository.findById(item.getProductId())
                                    .switchIfEmpty(Mono.error(new IllegalArgumentException("Product with ID " + item.getProductId() + " does not exist.")))
                                    .flatMap(product -> {
                                        if (!"A".equals(product.getStatus()) || product.getStock() <= 0) {
                                            return Mono.error(new IllegalArgumentException("Product with ID " + product.getId() + " is not available."));
                                        }
                                        if (item.getQuantity() > product.getStock()) {
                                            return Mono.error(new IllegalArgumentException("Not enough stock available for product with ID " + product.getId()));
                                        }
                                        // Actualiza el stock del producto
                                        product.setStock(product.getStock() - item.getQuantity());
                                        // Calcula el subtotal del producto
                                        double subtotal = product.getPrice() * item.getQuantity();
                                        item.setSubtotal(subtotal);
                                        return Mono.just(item);
                                    }))
                            .collectList()
                            .flatMap(orderItems -> {
                                // Calcula el total del pedido sumando los subtotales de todos los productos
                                double total = orderItems.stream().mapToDouble(OrderItem::getSubtotal).sum();
                                // Crea el pedido con la lista de items y el total calculado
                                Order order = new Order(orderRequestDto.getClientId(), orderItems, total, orderRequestDto.getStatus(), LocalDateTime.now());
                                return orderRepository.save(order);
                            });
                });
    }
}



