package com.ecommerce.order_managment.web;

import com.ecommerce.order_managment.domain.dto.UsersRequestDto;
import com.ecommerce.order_managment.domain.dto.UsersResponseDto;
import com.ecommerce.order_managment.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UsersController {
    private final UsersService usersService;

    @GetMapping(value="/list", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<UsersResponseDto> findAll() {
        return this.usersService.findAll();
    }

    @GetMapping(value="/listI", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<UsersResponseDto> findInactive() {
        return this.usersService.findInactive();
    }

    @GetMapping(value = "/find/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<UsersResponseDto> findById(@PathVariable Integer id) {
        return this.usersService.findById(id);
    }

    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<UsersResponseDto> update(@PathVariable Integer id, @RequestBody UsersRequestDto dto) {
        return this.usersService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<UsersResponseDto> create(@RequestBody UsersRequestDto dto) {
        return this.usersService.create(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.usersService.delete(id);
    }
}

