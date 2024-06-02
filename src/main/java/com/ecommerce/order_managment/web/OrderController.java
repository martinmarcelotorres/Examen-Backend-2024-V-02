package com.ecommerce.order_managment.web;

import com.ecommerce.order_managment.domain.dto.OrderRequestDto;
import com.ecommerce.order_managment.domain.dto.OrderResponseDto;
import com.ecommerce.order_managment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
    public Mono<OrderResponseDto> findById(@PathVariable Integer id) {
        return this.orderService.findById(id);
    }

    @PatchMapping("/{id}")
    public Mono<OrderResponseDto> updateOrder(@PathVariable String id, @RequestBody OrderRequestDto requestDto) {
        return orderService.updateOrder(id, requestDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<OrderResponseDto> create(@RequestBody OrderRequestDto dto) {
        return this.orderService.create(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.orderService.delete(id);
    }

    @PatchMapping(value = "/confirm/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<Void> confirmOrder(@PathVariable String id) {
        return this.orderService.confirmOrder(id);
    }
}
