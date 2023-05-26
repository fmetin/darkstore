package com.darkstore.depot.model.entity;

import com.darkstore.depot.model.enums.DepotStatus;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Depot")
public class Depot {

    @Id
    private String depotName;
    private DepotTypeEnum type;
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private DepotStatus status;
    private String costCenter;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate;
}
