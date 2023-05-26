package com.darkstore.depot.model.dto;

import com.darkstore.depot.annotation.UniqueProduct;
import com.darkstore.depot.model.enums.ProductTypeEnum;
import com.darkstore.depot.model.enums.ProductUnitTypeEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

import static com.darkstore.depot.model.response.DepotRestResponseMessage.*;

@Data
public class CreateProductRequestDto {

    @NotNull(message = MSG_VALIDATION_CONSTRAINT_NOTNULL)
    @Size(min = 1, max = 255, message = MSG_VALIDATION_CONSTRAINT_SIZE)
    @UniqueProduct
    private String name;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_NOTNULL)
    @Size(min = 1, max = 255, message = MSG_VALIDATION_CONSTRAINT_SIZE)
    private String sku;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_NOTNULL)
    @Size(min = 1, max = 255, message = MSG_VALIDATION_CONSTRAINT_SIZE)
    private String barcode;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_NOTNULL)
    private ProductTypeEnum type;
    private boolean isFrozen;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_NOTNULL)
    @Min(0)
    private BigDecimal price;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_NOTNULL)
    private ProductUnitTypeEnum unitType;
}
