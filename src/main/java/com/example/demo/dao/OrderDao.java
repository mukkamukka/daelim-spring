package com.example.demo.dao;

import com.example.demo.entity.Order;

import java.util.List;

public interface OrderDao {

    Order insertOrder(Order order);

    List<Order> findAllByOrder();

    List<Order> findOrderByUserId(String userId);

    List<Order> findByProductId(String productId);

    Order findOrderById(long id);
}
