package com.esi.week4refactored.orders.repository;

import java.util.List;

import com.esi.week4refactored.orders.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {

    List<Order> findByProductId(String productId);
}
