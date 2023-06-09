package com.example.demo.service;

import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto saveOrder(OrderDto orderDto);

    List<OrderDto> findAllByOrder();

    List<OrderDto> findOrderByUserId(String userId);

    List<OrderDto> findByProductId(String productId);

    OrderDto findOrderById(long id);
}
