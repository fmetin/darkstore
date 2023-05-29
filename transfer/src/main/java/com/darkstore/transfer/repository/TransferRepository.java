package com.darkstore.transfer.repository;

import com.darkstore.transfer.model.entity.Transfer;
import com.darkstore.transfer.model.enums.TransferStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    @Transactional
    @Modifying
    @Query("update Transfer t set t.status = ?1, t.updatedDate = ?2 where t.id = ?3")
    int updateStatusAndUpdatedDateById(TransferStatusEnum status, LocalDateTime updatedDate, long id);
}
