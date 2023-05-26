package com.darkstore.depot.repository;

import com.darkstore.depot.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    long countByDepotNameAndProductName(String depotName, String productName);
}
