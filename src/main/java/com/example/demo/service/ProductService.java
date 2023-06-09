package com.example.demo.service;

import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto changeProductName(Long number, String name, int price, int stock) throws Exception;

    ProductResponseDto saveProduct(ProductDto productDto);

    void deleteProduct(Long number) throws Exception;

    List<ProductDto> findAllBy();

    List<ProductDto> findByOrderByPriceDesc();

    List<ProductDto> findByName(String name);

    ProductDto findByNumber(Long number);
}
