package com.wintersolidstudios.ecommerce_api.repository;

import com.wintersolidstudios.ecommerce_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
}