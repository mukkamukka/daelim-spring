package com.example.demo.dao.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductOutOfStockException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderDaoImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order insertOrder(Order order) {
        Product product = productRepository.findProductById(order.getProductName());
        if (product.getStock() != 0) {
            product.setStock(product.getStock() - 1);
            productRepository.save(product);
        } else {
            throw new ProductOutOfStockException("재고가 없습니다.");
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAllByOrder() {
        return orderRepository.findAllByOrder();
    }

    @Override
    public List<Order> findOrderByUserId(String userId) {
        return orderRepository.findOrderByUserId(userId);
    }

    @Override
    public List<Order> findByProductId(String productId) {
        return orderRepository.findByProductId(productId);
    }

    @Override
    public Order findOrderById(long id) {
        return orderRepository.findOrderById(id);
    }
}
