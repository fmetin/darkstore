package com.darkstore.depot.service;

import com.darkstore.depot.model.dto.CreateStockRequestDto;

public interface StockService {
    void createStock(CreateStockRequestDto request);
}
