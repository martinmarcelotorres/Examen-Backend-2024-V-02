package com.ecommerce.order_managment.web;

import com.ecommerce.order_managment.domain.dto.ProductsRequestDto;
import com.ecommerce.order_managment.domain.dto.ProductsResponseDto;
import com.ecommerce.order_managment.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductsController {
    private final ProductsService productsService;

    @GetMapping(value="/list", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<ProductsResponseDto> findAll() {
        return this.productsService.findAll();
    }

    @GetMapping(value="/listI", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<ProductsResponseDto> findInactive() {
        return this.productsService.findInactive();
    }

    @GetMapping(value = "/find/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<ProductsResponseDto> findById(@PathVariable Integer id) {
        return this.productsService.findById(id);
    }

    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<ProductsResponseDto> update(@PathVariable Integer id, @RequestBody ProductsRequestDto dto) {
        return this.productsService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<ProductsResponseDto> create(@RequestBody ProductsRequestDto dto) {
        return this.productsService.create(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.productsService.delete(id);
    }
}
