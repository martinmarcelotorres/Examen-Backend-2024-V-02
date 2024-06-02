package com.ecommerce.order_managment.service;

import com.ecommerce.order_managment.domain.dto.ProductsRequestDto;
import com.ecommerce.order_managment.domain.dto.ProductsResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProductsService {

    Mono<ProductsResponseDto> findById(Integer id);

    Flux<ProductsResponseDto> findAll();

    Flux<ProductsResponseDto> findInactive();

    Mono<ProductsResponseDto> create(ProductsRequestDto request);

    Mono<ProductsResponseDto> update(Integer id, ProductsRequestDto request);

    Mono<Void> delete(Integer id);
}
