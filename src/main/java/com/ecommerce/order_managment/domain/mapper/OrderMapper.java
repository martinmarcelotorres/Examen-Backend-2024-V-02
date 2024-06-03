package com.ecommerce.order_managment.domain.mapper;

import com.ecommerce.order_managment.domain.dto.OrderCResponseDto;
import com.ecommerce.order_managment.domain.dto.OrderRequestDto;
import com.ecommerce.order_managment.domain.dto.OrderResponseDto;
import com.ecommerce.order_managment.domain.model.Order;
import com.ecommerce.order_managment.domain.model.OrderC;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

    public static Order toModel(OrderRequestDto dto) {
        return new Order(
                dto.getClientId(),
                dto.getItems(),
                dto.getTotal(),
                dto.getStatus() != null ? dto.getStatus() : "P",
                dto.getOrderDate() != null ? dto.getOrderDate() : LocalDateTime.now()
        );
    }

    public static Order toModel(String _id, OrderRequestDto dto) {
        return new Order(
                _id,
                dto.getClientId(),
                dto.getItems(),
                dto.getTotal(),
                dto.getStatus() != null ? dto.getStatus() : "P",
                dto.getOrderDate() != null ? dto.getOrderDate() : LocalDateTime.now()
        );
    }

    public static OrderResponseDto toDto(Order model) {
        return new OrderResponseDto(
                model.get_id(),
                model.getClientId(),
                model.getItems(),
                model.getTotal(),
                model.getStatus(),
                model.getOrderDate()
        );
    }

    public static OrderCResponseDto toDto(OrderC model) {
        return new OrderCResponseDto(
                model.get_id(),
                model.getClientId(),
                model.getItems(),
                model.getTotal(),
                model.getStatus(),
                model.getOrderDate(),
                model.getClient(),
                model.getProducts()
        );
    }
}