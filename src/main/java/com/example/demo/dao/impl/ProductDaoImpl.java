package com.example.demo.dao.impl;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product updateProduct(Long number, String name, int price, int stock) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);
        Product updateProduct;
        if (selectProduct.isPresent()) {
            Product product = selectProduct.get();
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }
        return updateProduct;
    }

    @Override
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);
        if (selectProduct.isPresent()) {
            Product product = selectProduct.get();
            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }

    @Override
    public List<Product> findAllBy() {
        return productRepository.findAllBy();
    }

    @Override
    public List<Product> findByOrderByPriceDesc() {
        return productRepository.findByOrderByPriceDesc();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findByNumber(Long number) {
        return productRepository.findByNumber(number);
    }
}
