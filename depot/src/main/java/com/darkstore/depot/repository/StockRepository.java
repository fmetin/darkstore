package com.darkstore.depot.repository;

import com.darkstore.depot.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByDepotNameAndProductName(String depotName, String productName);
    long countByDepotNameAndProductName(String depotName, String productName);

    List<Stock> findByDepotNameAndNumberOfStockGreaterThan(String depotName, long numberOfStock);

    @Transactional
    @Modifying
    @Query("update Stock s set s.numberOfStock = ?1, s.updatedDate = ?2 where s.depotName = ?3 and s.productName = ?4")
    int updateNumberOfStockAndUpdatedDateByDepotNameAndProductName(long numberOfStock, LocalDateTime updatedDate, String depotName, String productName);
}
