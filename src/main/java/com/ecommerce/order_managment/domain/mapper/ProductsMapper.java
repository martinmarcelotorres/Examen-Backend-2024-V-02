package com.ecommerce.order_managment.domain.mapper;

import com.ecommerce.order_managment.domain.dto.ProductsRequestDto;
import com.ecommerce.order_managment.domain.dto.ProductsResponseDto;
import com.ecommerce.order_managment.domain.model.Products;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class ProductsMapper {

    public static Products toModel(ProductsRequestDto dto) {
        return new Products(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStock(),
                dto.getStatus()
        );
    }

    public static Products toModel(Integer id, ProductsRequestDto dto) {
        return new Products(
                id,
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStock(),
                dto.getStatus()
        );
    }

    public static ProductsResponseDto toDto(Products model) {
        return new ProductsResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getPrice(),
                model.getStock(),
                model.getStatus()
        );
    }
}
