package com.wintersolidstudios.ecommerce_api.controller;

import com.wintersolidstudios.ecommerce_api.dto.CreateProductRequest;
import com.wintersolidstudios.ecommerce_api.dto.ProductResponse;
import com.wintersolidstudios.ecommerce_api.entity.Product;
import com.wintersolidstudios.ecommerce_api.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(
            ProductService service
    ) {
        this.service = service;
    }

    @PostMapping
    public ProductResponse createProduct(
            @Valid
            @RequestBody CreateProductRequest request
    ) {

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .stockQuantity(request.getStockQuantity())
                .build();

        Product savedProduct =
                service.createProduct(product);

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .category(savedProduct.getCategory())
                .stockQuantity(savedProduct.getStockQuantity())
                .build();
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(
            @PathVariable Long id
    ) {
        return service.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable Long id
    ) {
        service.deleteProduct(id);
    }
}