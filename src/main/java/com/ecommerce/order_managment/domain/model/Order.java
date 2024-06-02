package com.ecommerce.order_managment.domain.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String _id;
    private Integer clientId;
    private Integer productId;
    private Integer quantity;
    private Double total;
    private String status;
    private String orderDate;

    public Order(Integer clientId, Integer productId, Integer quantity, Double total, String status, String orderDate) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Order(String _id, Integer clientId, Integer productId, Integer quantity, Double total, String status, String orderDate) {
        this._id = _id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
        this.orderDate = orderDate;
    }
}
