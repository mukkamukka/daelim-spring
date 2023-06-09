package com.example.demo.service.impl;

import com.example.demo.dao.ProductDao;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductResponseDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name, int price, int stock) throws Exception {
        Product changeProduct = productDao.updateProduct(number, name, price, stock);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setNumber(changeProduct.getNumber());
        productResponseDto.setPrice(changeProduct.getPrice());
        productResponseDto.setStock(changeProduct.getStock());
        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDao.insertProduct(product);
        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());
        productResponseDto.setNumber(saveProduct.getNumber());
        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDao.deleteProduct(number);
    }

    @Override
    public List<ProductDto> findAllBy() {
        List<Product> products = productDao.findAllBy();

        return products.stream()
                .map(product -> new ProductDto(product.getName(), product.getPrice(), product.getStock()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByOrderByPriceDesc() {
        List<Product> products = productDao.findByOrderByPriceDesc();
        return products.stream()
                .map(product -> new ProductDto(product.getName(), product.getPrice(), product.getStock()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByName(String name) {
        List<Product> products = productDao.findByName(name);
        return products.stream()
                .map(product -> new ProductDto(product.getName(), product.getPrice(), product.getStock()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findByNumber(Long number) {
        Product products = productDao.findByNumber(number);
        return new ProductDto(products.getName(), products.getPrice(), products.getStock());
    }
}
