package com.darkstore.depot.repository;

import com.darkstore.depot.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByName(String name);
    long countByName(String name);
}
