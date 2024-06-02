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
public class Users {

    @Id
    private Integer id;
    private String name;
    private String last_name;
    private String type;
    private String password;
    private String email;
    private String status;

    public Users(String name, String last_name, String type, String password, String email, String status) {
        this.name = name;
        this.last_name = last_name;
        this.type = type;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    public Users(Integer id, String name, String last_name, String type, String password, String email, String status) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.type = type;
        this.password = password;
        this.email = email;
        this.status = status;
    }
}
