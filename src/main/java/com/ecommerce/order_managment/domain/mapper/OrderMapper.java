package com.ecommerce.order_managment.domain.mapper;

import com.ecommerce.order_managment.domain.dto.OrderRequestDto;
import com.ecommerce.order_managment.domain.dto.OrderResponseDto;
import com.ecommerce.order_managment.domain.model.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

    public static Order toModel(OrderRequestDto dto) {
        return new Order(
                dto.getClientId(),
                dto.getProductId(),
                dto.getQuantity(),
                dto.getTotal(),
                dto.getStatus(),
                dto.getOrderDate()
        );
    }

    public static Order toModel(String id, OrderRequestDto dto) {
        return new Order(
                id,
                dto.getClientId(),
                dto.getProductId(),
                dto.getQuantity(),
                dto.getTotal(),
                dto.getStatus(),
                dto.getOrderDate()
        );
    }

    public static OrderResponseDto toDto(Order model) {
        return new OrderResponseDto(
                model.get_id(),
                model.getClientId(),
                model.getProductId(),
                model.getQuantity(),
                model.getTotal(),
                model.getStatus(),
                model.getOrderDate()
        );
    }
}
