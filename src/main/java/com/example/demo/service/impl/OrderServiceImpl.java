package com.example.demo.service.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderResponseDto;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public OrderResponseDto saveOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setProductId(orderDto.getProductId());
        order.setProductName(orderDto.getProductName());
        order.setUserId(orderDto.getUserId());
        order.setUserName(orderDto.getUserName());
        order.setPrice(orderDto.getPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        
        Order saveOrder = orderDao.insertOrder(order);
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setProductId(saveOrder.getProductId());
        orderResponseDto.setProductName(saveOrder.getProductName());
        orderResponseDto.setUserId(saveOrder.getUserId());
        orderResponseDto.setUserName(saveOrder.getUserName());
        orderResponseDto.setPrice(saveOrder.getPrice());
        orderResponseDto.setId(saveOrder.getId());
        return orderResponseDto;
    }

    @Override
    public List<OrderDto> findAllByOrder() {
        List<Order> orders = orderDao.findAllByOrder();

        return orders.stream()
                .map(order -> new OrderDto(order.getProductId(), order.getProductName(), order.getUserId(), order.getUserName(), order.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findOrderByUserId(String userId) {
        List<Order> orders = orderDao.findOrderByUserId(userId);

        return orders.stream()
                .map(order -> new OrderDto(order.getProductId(), order.getProductName(), order.getUserId(), order.getUserName(), order.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findByProductId(String productId) {
        List<Order> orders = orderDao.findByProductId(productId);

        return orders.stream()
                .map(order -> new OrderDto(order.getProductId(), order.getProductName(), order.getUserId(), order.getUserName(), order.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findOrderById(long id) {
        Order order = orderDao.findOrderById(id);
        return new OrderDto(order.getProductId(), order.getProductName(), order.getUserId(), order.getUserName(), order.getPrice());
    }

}
