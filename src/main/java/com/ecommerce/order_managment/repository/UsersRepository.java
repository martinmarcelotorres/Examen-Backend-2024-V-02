package com.ecommerce.order_managment.repository;

import com.ecommerce.order_managment.domain.model.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends ReactiveCrudRepository<Users, Integer> {
}
