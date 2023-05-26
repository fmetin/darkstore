package com.darkstore.depot.model.dto;

import com.darkstore.depot.annotation.UniqueDepot;
import com.darkstore.depot.annotation.UniqueProduct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.darkstore.depot.model.response.DepotRestResponseMessage.*;
import static com.darkstore.depot.model.response.DepotRestResponseMessage.MSG_VALIDATION_CONSTRAINT_SIZE;

@Data
public class CreateStockRequestDto {
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_DEPOT_NAME_NOTNULL)
    @Size(min = 1, max = 255, message = MSG_VALIDATION_CONSTRAINT_DEPOT_NAME_SIZE)
    private String depotName;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_NOTNULL)
    @Size(min = 1, max = 255, message = MSG_VALIDATION_CONSTRAINT_SIZE)
    private String productName;
    @Min(0)
    private long numberOfStock;
}
