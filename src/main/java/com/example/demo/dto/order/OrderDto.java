package com.example.demo.dto.order;

import com.example.demo.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String productId;
    private String productName;
    private String userId;
    private String userName;
    private int price;

    public OrderDto(Order order) {
    }
}
