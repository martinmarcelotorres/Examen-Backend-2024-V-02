package com.ecommerce.order_managment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OrderResponseDto implements Serializable {
    private static final long serialVersionUID = 8735757125749188522L;

    private String _id;
    private Integer clientId;
    private Integer productId;
    private Integer quantity;
    private Double total;
    private String status;
    private String orderDate;
}
