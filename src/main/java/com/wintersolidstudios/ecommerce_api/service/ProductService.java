package com.wintersolidstudios.ecommerce_api.service;

import com.wintersolidstudios.ecommerce_api.entity.Product;
import com.wintersolidstudios.ecommerce_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(
            ProductRepository repository
    ) {
        this.repository = repository;
    }

    public Product createProduct(
            Product product
    ) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(
            Long id
    ) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found"
                        )
                );
    }

    public void deleteProduct(
            Long id
    ) {
        repository.deleteById(id);
    }
}