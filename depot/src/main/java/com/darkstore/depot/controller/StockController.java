package com.darkstore.depot.controller;

import com.darkstore.depot.common.response.model.RestResponse;
import com.darkstore.depot.model.dto.CreateStockRequestDto;
import com.darkstore.depot.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
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
}
