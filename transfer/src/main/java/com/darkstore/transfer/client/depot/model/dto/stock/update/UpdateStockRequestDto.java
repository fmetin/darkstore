package com.darkstore.transfer.client.depot.model.dto.stock.update;

import lombok.Data;

@Data
public class UpdateStockRequestDto {
    private String depotName;
    private String productName;
    private long numberOfStock;
}
