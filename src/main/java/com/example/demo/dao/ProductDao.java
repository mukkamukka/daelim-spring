package com.example.demo.dao;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductDao {

    Product updateProduct(Long number, String name, int price, int stock) throws Exception;

    Product insertProduct(Product product);

    void deleteProduct(Long number) throws Exception;

    List<Product> findAllBy();

    List<Product> findByOrderByPriceDesc();

    List<Product> findByName(String name);

   Product findByNumber(Long number);
}
