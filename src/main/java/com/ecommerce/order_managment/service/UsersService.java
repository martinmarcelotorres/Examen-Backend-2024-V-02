package com.ecommerce.order_managment.service;


import com.ecommerce.order_managment.domain.dto.UsersRequestDto;
import com.ecommerce.order_managment.domain.dto.UsersResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface UsersService {

    Mono<UsersResponseDto> findById(Integer id);

    Flux<UsersResponseDto> findAll();

    Flux<UsersResponseDto> findInactive();

    Mono<UsersResponseDto> create(UsersRequestDto request);

    Mono<UsersResponseDto> update(Integer id, UsersRequestDto request);

    Mono<Void> delete(Integer id);
}