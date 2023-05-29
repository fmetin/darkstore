package com.darkstore.transfer.client.depot.model.dto.stock.info;

import lombok.Data;

@Data
public class StockInfoResponseDto {
    private DepotInfoResponseDto to;
    private DepotInfoResponseDto from;
    private boolean hasStock;
}
