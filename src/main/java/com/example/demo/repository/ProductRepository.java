package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p")
    List<Product> findAllBy();

    List<Product> findByOrderByPriceDesc();

    List<Product> findByName(String name);

    Product findByNumber(Long number);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findProductById(@Param("name") String name);
}
