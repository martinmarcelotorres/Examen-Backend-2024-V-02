package com.ecommerce.order_managment.service;

import com.ecommerce.order_managment.domain.dto.OrderRequestDto;
import com.ecommerce.order_managment.domain.dto.OrderResponseDto;
import com.ecommerce.order_managment.domain.model.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface OrderService {

    Mono<OrderResponseDto> findById(String _id);

    Flux<OrderResponseDto> findAll();

    Flux<OrderResponseDto> findPending();

    Flux<OrderResponseDto> findConfirm();

    Mono<OrderResponseDto> create(OrderRequestDto request);

    Mono<OrderResponseDto> updateOrder(String _id, OrderRequestDto request);

    Mono<Void> confirmOrder(String _id);

    Mono<Order> createOrder(OrderRequestDto orderRequestDto);
}
