package com.darkstore.depot.repository;

import com.darkstore.depot.model.entity.Depot;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepotRepository extends JpaRepository<Depot, String> {
    long countByDepotName(String depotName);

    long countByType(DepotTypeEnum type);

    Optional<Depot> findByDepotName(String depotName);
}
