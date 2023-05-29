package com.darkstore.depot.service;

import com.darkstore.depot.model.dto.CreateStockRequestDto;
import com.darkstore.depot.model.dto.UpdateStockListRequestDto;
import com.darkstore.depot.model.dto.UpdateStockRequestDto;

public interface StockService {
    void createStock(CreateStockRequestDto request);

    void updateStock(UpdateStockRequestDto request);

    void updateStockList(UpdateStockListRequestDto request);
}
