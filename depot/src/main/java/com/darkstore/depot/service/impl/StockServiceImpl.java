package com.darkstore.depot.service.impl;

import com.darkstore.depot.common.exception.RestException;
import com.darkstore.depot.common.properties.CommonProperties;
import com.darkstore.depot.locker.DistributedLocker;
import com.darkstore.depot.locker.LockExecutionResult;
import com.darkstore.depot.mapper.StockMapper;
import com.darkstore.depot.model.dto.CreateStockRequestDto;
import com.darkstore.depot.model.dto.UpdateStockListRequestDto;
import com.darkstore.depot.model.dto.UpdateStockRequestDto;
import com.darkstore.depot.model.entity.Depot;
import com.darkstore.depot.model.entity.Stock;
import com.darkstore.depot.model.enums.DepotStatusEnum;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import com.darkstore.depot.model.response.DepotRestResponseCode;
import com.darkstore.depot.repository.StockRepository;
import com.darkstore.depot.service.DepotService;
import com.darkstore.depot.service.ProductService;
import com.darkstore.depot.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final DepotService depotService;
    private final ProductService productService;

    private final DistributedLocker locker;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper, DepotService depotService, ProductService productService, DistributedLocker locker) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
        this.depotService = depotService;
        this.productService = productService;
        this.locker = locker;
    }

    @Override
    public void createStock(CreateStockRequestDto request) {
        checkCreateStockRequest(request);
        stockRepository.save(stockMapper.mapCreateStockRequestDtoToStock(request));
    }

    @Override
    public void updateStock(UpdateStockRequestDto request) {
        String key = "depot-api-update-stock-" + request.getDepotName() + "-" + request.getProductName();
        LockExecutionResult<String> result = locker.lock(key, CommonProperties.REDIS_LOCK_ACQUIRED_SECONDS, CommonProperties.REDIS_LOCK_TIMEOUT_SECONDS, () -> {
            long currentStock = checkUpdateRequest(request);
            long updatedStock = currentStock + request.getNumberOfStock();
            if (updatedStock < 0)
                throw new RestException(DepotRestResponseCode.DPT_STOCK_CANNOT_BE_LOWER_THAN_ZERO);
            stockRepository.updateNumberOfStockAndUpdatedDateByDepotNameAndProductName(updatedStock, LocalDateTime.now(), request.getDepotName(), request.getProductName());
            return null;
        });
        if (result.hasException())
            throw (RestException) result.exception;

    }

    @Transactional
    @Override
    public void updateStockList(UpdateStockListRequestDto request) {
        for (UpdateStockRequestDto requestDto :
                request.getStockList()) {
            Optional<Stock> optionalStock = stockRepository.findByDepotNameAndProductName(requestDto.getDepotName(), requestDto.getProductName());
            if (optionalStock.isEmpty())
                stockRepository.save(stockMapper.mapUpdateStockRequestDtoToStock(requestDto));
            else {
                updateStock(requestDto);
            }
        }
    }

    private long checkUpdateRequest(UpdateStockRequestDto request) {
        if (productService.findByName(request.getProductName()).isEmpty())
            throw new RestException(DepotRestResponseCode.DPT_PRODUCT_DOESNT_EXIST);
        Optional<Depot> optionalDepot = depotService.findByDepotName(request.getDepotName());
        if (optionalDepot.isEmpty())
            throw new RestException(DepotRestResponseCode.DPT_DEPOT_DOESNT_EXIST);
        Optional<Stock> optionalStock = stockRepository.findByDepotNameAndProductName(request.getDepotName(), request.getProductName());
        if (optionalStock.isEmpty())
            throw new RestException(DepotRestResponseCode.DPT_PRODUCT_DOESNT_EXIST);

        return optionalStock.get().getNumberOfStock();
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
