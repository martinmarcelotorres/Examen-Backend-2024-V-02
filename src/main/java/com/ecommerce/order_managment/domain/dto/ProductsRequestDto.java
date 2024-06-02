package com.ecommerce.order_managment.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductsRequestDto implements Serializable {
    private static final long serialVersionUID = 8222253670338491507L;

    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String status;
}
