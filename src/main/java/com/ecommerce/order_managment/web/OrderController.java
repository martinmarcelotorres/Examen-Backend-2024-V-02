package com.ecommerce.order_managment.web;

import com.ecommerce.order_managment.domain.dto.OrderRequestDto;
import com.ecommerce.order_managment.domain.dto.OrderResponseDto;
import com.ecommerce.order_managment.domain.dto.ProductsRequestDto;
import com.ecommerce.order_managment.domain.dto.ProductsResponseDto;
import com.ecommerce.order_managment.domain.model.Order;
import com.ecommerce.order_managment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<OrderResponseDto> findAll() {
        return this.orderService.findAll();
    }

    @GetMapping(value = "/listP", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<OrderResponseDto> findPending() {
        return this.orderService.findPending();
    }

    @GetMapping(value = "/listC", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<OrderResponseDto> findConfirm() {
        return this.orderService.findConfirm();
    }

    @GetMapping(value = "/find/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<OrderResponseDto> findById(@PathVariable("id") String _id) {
        return this.orderService.findById(_id);
    }

    @PatchMapping("/{id}")
    public Mono<OrderResponseDto> updateOrder(@PathVariable String id, @RequestBody OrderRequestDto requestDto) {
        return orderService.updateOrder(id, requestDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Order> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto)
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())));
    }

    @PatchMapping(value = "/confirm/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<Void> confirmOrder(@PathVariable("id") String _id) {
        return this.orderService.confirmOrder(_id);
    }


}
