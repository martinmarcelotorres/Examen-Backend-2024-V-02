package com.ecommerce.order_managment.domain.dto;

import lombok.Data;



@Data
public class OrderRequestDto {

    private static final long serialVersionUID = 8222253670338491507L;

    private String _id;
    private Integer clientId;
    private Integer productId;
    private Integer quantity;
    private Double total;
    private String status;
    private String orderDate;
}

