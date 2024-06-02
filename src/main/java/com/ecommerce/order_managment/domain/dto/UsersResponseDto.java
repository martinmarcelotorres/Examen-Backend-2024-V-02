package com.ecommerce.order_managment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UsersResponseDto implements Serializable {
    private static final long serialVersionUID = 8735757125749188522L;

    private Integer id;
    private String name;
    private String last_name;
    private String type;
    private String password;
    private String email;
    private String status;

}
