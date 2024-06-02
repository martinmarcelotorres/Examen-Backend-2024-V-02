package com.ecommerce.order_managment.repository;


import com.ecommerce.order_managment.domain.model.Products;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsRepository extends ReactiveCrudRepository<Products, Integer> {
}
