package com.ecommerce.order_managment.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersRequestDto implements Serializable {
    private static final long serialVersionUID = 8222253670338491507L;

    private Integer id;
    private String name;
    private String last_name;
    private String type;
    private String password;
    private String email;
    private String status;
}
