package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o")
    List<Order> findAllByOrder();

    List<Order> findOrderByUserId(String user_id);

    List<Order> findByProductId(String product_id);

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Order findOrderById(@Param("id") long id);
}
