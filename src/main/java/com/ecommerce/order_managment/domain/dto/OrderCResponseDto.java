package com.ecommerce.order_managment.domain.dto;

import com.ecommerce.order_managment.domain.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderCResponseDto {
    private String _id;
    private Integer clientId;
    private List<OrderItem> items = new ArrayList<>();;
    private Double total;
    private String status = "P";
    private LocalDateTime orderDate = LocalDateTime.now();

    private UsersResponseDto client;
    private List<ProductsResponseDto> products;
}
