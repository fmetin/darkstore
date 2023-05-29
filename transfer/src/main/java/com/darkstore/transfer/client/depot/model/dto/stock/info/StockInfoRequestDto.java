package com.darkstore.transfer.client.depot.model.dto.stock.info;

import lombok.Data;

@Data
public class StockInfoRequestDto {
    private String to;
    private String from;
    private String productName;
    private long numberOfStock;
}
