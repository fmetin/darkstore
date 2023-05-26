package com.darkstore.depot.service.impl;

import com.darkstore.depot.common.exception.RestException;
import com.darkstore.depot.mapper.StockMapper;
import com.darkstore.depot.model.dto.CreateStockRequestDto;
import com.darkstore.depot.model.entity.Depot;
import com.darkstore.depot.model.enums.DepotStatusEnum;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import com.darkstore.depot.model.response.DepotRestResponseCode;
import com.darkstore.depot.repository.StockRepository;
import com.darkstore.depot.service.DepotService;
import com.darkstore.depot.service.ProductService;
import com.darkstore.depot.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final DepotService depotService;
    private final ProductService productService;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper, DepotService depotService, ProductService productService) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
        this.depotService = depotService;
        this.productService = productService;
    }

    @Override
    public void createStock(CreateStockRequestDto request) {
        checkCreateStockRequest(request);
        stockRepository.save(stockMapper.mapCreateStockRequestDtoToStock(request));
    }

    private void checkCreateStockRequest(CreateStockRequestDto request) {
        checkDepot(request);
        checkProduct(request);
    }

    private void checkProduct(CreateStockRequestDto request) {
        if (productService.findByName(request.getProductName()).isEmpty())
            throw new RestException(DepotRestResponseCode.DPT_PRODUCT_DOESNT_EXIST);
        if (stockRepository.countByDepotNameAndProductName(request.getDepotName(), request.getProductName()) > 0)
            throw new RestException(DepotRestResponseCode.DPT_PRODUCT_ALREADY_EXIST);
    }

    private void checkDepot(CreateStockRequestDto request) {
        Optional<Depot> optionalDepot = depotService.findByDepotName(request.getDepotName());
        if (optionalDepot.isEmpty())
            throw new RestException(DepotRestResponseCode.DPT_DEPOT_DOESNT_EXIST);

        Depot depot = optionalDepot.get();
        if (!depot.getType().equals(DepotTypeEnum.MAIN))
            throw new RestException(DepotRestResponseCode.DPT_STOCK_ONLY_CAN_BE_DEFINED_TO_MAIN_DEPOT);
        if (!depot.getStatus().equals(DepotStatusEnum.OPEN))
            throw new RestException(DepotRestResponseCode.DPT_DEPOT_CLOSED);
    }
}
