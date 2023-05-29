package com.darkstore.transfer.client.depot.model.dto.stock.info;

import com.darkstore.transfer.common.response.model.RestResponseHeader;
import lombok.Data;

@Data
public class StockInfoRestResponseDto {
    private RestResponseHeader header;
    private StockInfoResponseDto detail;
}
