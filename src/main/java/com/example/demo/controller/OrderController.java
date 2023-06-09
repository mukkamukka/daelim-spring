package com.example.demo.controller;

import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderResponseDto;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //주문등록
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<OrderResponseDto> orderReg(@RequestBody OrderDto orderDto) {
        OrderResponseDto orderResponseDto = orderService.saveOrder(orderDto);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

    //주문리스트
    //ADMIN
    @GetMapping("/list")
    public ResponseEntity<List<OrderDto>> orderList() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllByOrder());
    }

    //상품별주문리스트-구매자아이디를통해
    //ADMIN
    @GetMapping("/listByUserId")
    public ResponseEntity<List<OrderDto>> orderListByUserId(String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderByUserId(userId));
    }

    //상품별주문리스트-상품아이디를통해
    //ADMIN
    @GetMapping("/listByProductId")
    public ResponseEntity<List<OrderDto>> orderListByProductId(String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByProductId(productId));
    }

    //주문정보-아이디를통해
    @GetMapping("/")
    public ResponseEntity<OrderDto> orderInfo(long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderById(id));
    }
}
