package com.darkstore.depot.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue
    private long id;
    private String depotName;
    private String productName;
    private long numberOfStock;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate;
}
