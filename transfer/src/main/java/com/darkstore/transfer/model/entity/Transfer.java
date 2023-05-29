package com.darkstore.transfer.model.entity;

import com.darkstore.transfer.model.enums.TransferStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Transfer")
public class Transfer {
    @Id
    @GeneratedValue
    private long id;
    private String fromDepotName;
    private String toDepotName;
    private String productName;
    private long numberOfStock;
    @Enumerated(EnumType.STRING)
    private TransferStatusEnum status;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate;
}
