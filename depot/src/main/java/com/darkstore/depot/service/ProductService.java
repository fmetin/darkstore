package com.darkstore.depot.service;

import com.darkstore.depot.model.dto.CreateProductRequestDto;
import com.darkstore.depot.model.entity.Product;

import java.util.Optional;

public interface ProductService {
    void createProduct(CreateProductRequestDto request);

    long countByName(String name);

    Optional<Product> findByName(String name);
}
