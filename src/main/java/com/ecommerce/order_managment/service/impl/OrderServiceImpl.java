package com.ecommerce.order_managment.service.impl;

import com.ecommerce.order_managment.domain.dto.OrderRequestDto;
import com.ecommerce.order_managment.domain.dto.OrderResponseDto;
import com.ecommerce.order_managment.domain.mapper.OrderMapper;
import com.ecommerce.order_managment.exception.ResourceNotFoundException;
import com.ecommerce.order_managment.repository.OrderRepository;
import com.ecommerce.order_managment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Mono<OrderResponseDto> findById(Integer _id) {
        return orderRepository.findById(_id.toString())
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
                    // Actualizar los detalles del pedido
                    order.setClientId(requestDto.getClientId());
                    order.setProductId(requestDto.getProductId());
                    order.setTotal(requestDto.getTotal());
                    order.setStatus(requestDto.getStatus());
                    order.setOrderDate(requestDto.getOrderDate());

                    // Guardar los cambios en la base de datos no relacional
                    return orderRepository.save(order);
                })
                .map(OrderMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Integer _id) {
        return orderRepository.findById(_id.toString())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Order not found with id " + _id.toString())))
                .flatMap(orderRepository::delete)
                .doOnSuccess(unused -> log.info("Order deleted with id: {}", _id.toString()))
                .then();
    }

    @Override
    public Mono<Void> confirmOrder(String id) {
        return orderRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Order not found with id " + id)))
                .flatMap(order -> {
                    order.setStatus("C");
                    return orderRepository.save(order);
                })
                .doOnSuccess(unused -> log.info("Order confirmed with id: {}", id))
                .then();
    }
}

