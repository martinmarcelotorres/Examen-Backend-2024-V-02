package com.ecommerce.order_managment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer productId;
    private Integer quantity;
    private Double subtotal;
}
