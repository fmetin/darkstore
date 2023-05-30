package com.darkstore.depot.service;

import com.darkstore.depot.model.dto.*;
import com.darkstore.depot.model.entity.Stock;

import java.util.List;

public interface StockService {
    void createStock(CreateStockRequestDto request);

    void updateStock(UpdateStockRequestDto request);

    void updateStockList(UpdateStockListRequestDto request);

    StockInfoResponseDto stockInfo(StockInfoRequestDto request);

    List<Stock> findByDepotNameAndNumberOfStock(String depotName);

}
