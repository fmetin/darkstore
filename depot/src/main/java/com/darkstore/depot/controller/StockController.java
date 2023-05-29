package com.darkstore.depot.controller;

import com.darkstore.depot.common.response.model.RestResponse;
import com.darkstore.depot.model.dto.CreateStockRequestDto;
import com.darkstore.depot.model.dto.StockInfoRequestDto;
import com.darkstore.depot.model.dto.UpdateStockListRequestDto;
import com.darkstore.depot.model.dto.UpdateStockRequestDto;
import com.darkstore.depot.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/v1/create-stock")
    public ResponseEntity<Object> createStock(@Valid @RequestBody CreateStockRequestDto request) {
        stockService.createStock(request);
        return ResponseEntity.ok(new RestResponse<>());
    }

    @PostMapping("/v1/update-stock")
    public ResponseEntity<Object> updateStock(@Valid @RequestBody UpdateStockRequestDto request) {
        stockService.updateStock(request);
        return ResponseEntity.ok(new RestResponse<>());
    }

    @PostMapping("/v1/update-stock-list")
    public ResponseEntity<Object> updateStockList(@Valid @RequestBody UpdateStockListRequestDto request) {
        stockService.updateStockList(request);
        return ResponseEntity.ok(new RestResponse<>());
    }

    @PostMapping("/v1/stock-info")
    public ResponseEntity<Object> stockInfo(@Valid @RequestBody StockInfoRequestDto request) {
        return ResponseEntity.ok(new RestResponse<>(stockService.stockInfo(request)));
    }
}
