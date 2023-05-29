package com.darkstore.depot.model.dto;

import lombok.Data;

@Data
public class StockInfoResponseDto {
    private DepotInfoResponseDto to;
    private DepotInfoResponseDto from;
    private boolean hasStock;
}
