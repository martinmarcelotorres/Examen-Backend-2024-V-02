package com.ecommerce.order_managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class OrderManagmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(OrderManagmentSystemApplication.class);
        application.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        application.run(args);
    }
}



