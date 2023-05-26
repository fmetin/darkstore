package com.darkstore.depot.repository;

import com.darkstore.depot.model.entity.Depot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepotRepository extends JpaRepository<Depot, String> {
}
