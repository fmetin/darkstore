package com.darkstore.depot.model.entity;

import com.darkstore.depot.model.enums.ProductTypeEnum;
import com.darkstore.depot.model.enums.ProductUnitTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product {

    @Id
    private String name;
    private String sku;
    private String barcode;
    @Enumerated(EnumType.STRING)
    private ProductTypeEnum type;
    private boolean isFrozen;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductUnitTypeEnum unitType;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate;
}
