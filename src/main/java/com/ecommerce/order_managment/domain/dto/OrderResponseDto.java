package com.ecommerce.order_managment.domain.dto;

import com.ecommerce.order_managment.domain.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDto implements Serializable {
    private static final long serialVersionUID = 8735757125749188522L;

    private String _id;
    private Integer clientId;
    private List<OrderItem> items;
    private Double total;
    private String status = "P";
    private LocalDateTime orderDate = LocalDateTime.now();
}


