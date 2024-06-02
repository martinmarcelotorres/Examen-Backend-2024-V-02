package com.ecommerce.order_managment.service.impl;

import com.ecommerce.order_managment.domain.dto.ProductsRequestDto;
import com.ecommerce.order_managment.domain.dto.ProductsResponseDto;
import com.ecommerce.order_managment.domain.mapper.ProductsMapper;
import com.ecommerce.order_managment.exception.ResourceNotFoundException;
import com.ecommerce.order_managment.repository.ProductsRepository;
import com.ecommerce.order_managment.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ecommerce.order_managment.domain.mapper.ProductsMapper.toModel;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    @Override
    public Mono<ProductsResponseDto> findById(Integer id) {
        return this.productsRepository.findById(id)
                .map(ProductsMapper::toDto);
    }

    @Override
    public Flux<ProductsResponseDto> findAll() {
        return this.productsRepository.findAll()
                .filter(programa -> programa.getStatus().equals("A"))
                .map(ProductsMapper::toDto);
    }

    @Override
    public Flux<ProductsResponseDto> findInactive() {
        return this.productsRepository.findAll()
                .filter(programa -> programa.getStatus().equals("I"))
                .map(ProductsMapper::toDto);
    }

    @Override
    public Mono<ProductsResponseDto> create(ProductsRequestDto request) {
        return this.productsRepository.save(toModel(request))
                .map(ProductsMapper::toDto);
    }

    @Override
    public Mono<ProductsResponseDto> update(Integer id, ProductsRequestDto request) {
        return this.productsRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El id : " + id + " no fue encontrado")))
                .flatMap(programs -> this.productsRepository.save(toModel(programs.getId(), request)))
                .map(ProductsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return this.productsRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El id : " + id + " no fue encontrado")))
                .flatMap(producto-> {
                    producto.setStatus("I");
                    return this.productsRepository.save(producto);
                })
                .doOnSuccess(unused -> log.info("Se elimino el producto con id: {}", id))
                .then();

    }
}
