package com.ecommerce.order_managment.repository;

import com.ecommerce.order_managment.domain.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
}