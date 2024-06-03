package com.ecommerce.order_managment.domain.model;

import com.ecommerce.order_managment.domain.dto.ProductsResponseDto;
import com.ecommerce.order_managment.domain.dto.UsersResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")
public class OrderC {

    @Id
    private String _id;
    private Integer clientId;
    private List<OrderItem> items = new ArrayList<>();
    private Double total;
    private String status = "P";
    private LocalDateTime orderDate = LocalDateTime.now();
    private UsersResponseDto client;
    private List<ProductsResponseDto> products;



    public OrderC(Integer clientId, List<OrderItem> items, Double total, String status, LocalDateTime orderDate) {
        this.clientId = clientId;
        this.items = items;
        this.total = total;
        this.status = status;
        this.orderDate = orderDate;
    }

    public OrderC(String _id, Integer clientId, List<OrderItem> items, Double total, String status, LocalDateTime orderDate) {
        this._id = _id;
        this.clientId = clientId;
        this.items = items;
        this.total = total;
        this.status = status;
        this.orderDate = orderDate;
    }

    public OrderC(UsersResponseDto client, List<ProductsResponseDto> products) {
        this.client = client;
        this.products = products;
    }

}
