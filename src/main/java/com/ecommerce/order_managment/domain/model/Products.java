package com.ecommerce.order_managment.domain.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@Setter
@NoArgsConstructor
public class Products {
    @Id
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String status;

    public Products(String name, String description, Double price, Integer stock, String status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

    public Products(Integer id, String name, String description, Double price, Integer stock, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }
}
