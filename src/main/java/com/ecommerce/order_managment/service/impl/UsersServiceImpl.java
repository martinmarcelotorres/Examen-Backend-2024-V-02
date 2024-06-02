package com.ecommerce.order_managment.service.impl;



import com.ecommerce.order_managment.domain.dto.UsersRequestDto;
import com.ecommerce.order_managment.domain.dto.UsersResponseDto;
import com.ecommerce.order_managment.domain.mapper.UsersMapper;
import com.ecommerce.order_managment.exception.ResourceNotFoundException;

import com.ecommerce.order_managment.repository.UsersRepository;
import com.ecommerce.order_managment.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ecommerce.order_managment.domain.mapper.UsersMapper.toModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public Mono<UsersResponseDto> findById(Integer id) {
        return this.usersRepository.findById(id)
                .map(UsersMapper::toDto);
    }

    @Override
    public Flux<UsersResponseDto> findAll() {
        return this.usersRepository.findAll()
                .filter(users -> users.getStatus().equals("A"))
                .map(UsersMapper::toDto);
    }

    @Override
    public Flux<UsersResponseDto> findInactive() {
        return this.usersRepository.findAll()
                .filter(programa -> programa.getStatus().equals("I"))
                .map(UsersMapper::toDto);
    }

    @Override
    public Mono<UsersResponseDto> create(UsersRequestDto request) {
        return this.usersRepository.save(toModel(request))
                .map(UsersMapper::toDto);
    }

    @Override
    public Mono<UsersResponseDto> update(Integer id, UsersRequestDto request) {
        return this.usersRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El id : " + id + " no fue encontrado")))
                .flatMap(users -> this.usersRepository.save(toModel(users.getId(), request)))
                .map(UsersMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return this.usersRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El id : " + id + " no fue encontrado")))
                .flatMap(user-> {
                    user.setStatus("I");
                    return this.usersRepository.save(user);
                })
                .doOnSuccess(unused -> log.info("Se elimino el user con id: {}", id))
                .then();

    }
}
