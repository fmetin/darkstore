package com.darkstore.depot.repository;

import com.darkstore.depot.model.entity.Depot;
import com.darkstore.depot.model.enums.DepotStatusEnum;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DepotRepository extends JpaRepository<Depot, String> {
    @Transactional
    @Modifying
    @Query("update Depot d set d.status = ?1, d.updatedDate = ?2 where d.depotName = ?3")
    int updateStatusAndUpdatedDateByDepotName(DepotStatusEnum status, LocalDateTime updatedDate, String depotName);
    long countByDepotName(String depotName);

    long countByType(DepotTypeEnum type);

    Optional<Depot> findByDepotName(String depotName);

    Optional<Depot> findByType(DepotTypeEnum type);
}
