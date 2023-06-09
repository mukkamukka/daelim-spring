package com.example.demo.controller;

import com.example.demo.dto.product.ChangeProductDto;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductResponseDto;
import com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //상품 수정: 상품명, 가격, 재고
    //ADMIN
    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ChangeProductDto changeProductDto) throws Exception{
        ProductResponseDto productResponseDTO = productService.changeProductName(changeProductDto.getNumber(), changeProductDto.getName(), changeProductDto.getPrice(), changeProductDto.getStock());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    //상품 등록
    //ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDTO = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    //상품삭제
    //ADMIN
    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception{
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    //상품 리스트
    //ANY
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> productList() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllBy());
    }

    //상품리스트:내림차순
    //ANY
    @GetMapping("/listOrderByPrice")
    public ResponseEntity<List<ProductDto>> productListOrderByPriceDesc() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByOrderByPriceDesc());
    }

    //상품리스트-이름을통해가져오기
    //ANY
    @GetMapping("/byName")
    public ResponseEntity<List<ProductDto>> productOrderByName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByName(name));
    }

    //상품정보-아이디를통해가져오기
    //ANY
    @GetMapping("/")
    public ResponseEntity<ProductDto> productOrderById(Long number) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByNumber(number));
    }
}
