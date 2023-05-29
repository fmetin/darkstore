package com.darkstore.transfer.repository;

import com.darkstore.transfer.model.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
