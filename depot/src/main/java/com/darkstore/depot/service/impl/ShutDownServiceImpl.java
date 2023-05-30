package com.darkstore.depot.service.impl;

import com.darkstore.depot.common.exception.RestException;
import com.darkstore.depot.common.properties.CommonProperties;
import com.darkstore.depot.locker.DistributedLocker;
import com.darkstore.depot.locker.LockExecutionResult;
import com.darkstore.depot.model.dto.ShutDownDepotRequestDto;
import com.darkstore.depot.model.entity.Depot;
import com.darkstore.depot.model.entity.Stock;
import com.darkstore.depot.model.enums.DepotStatusEnum;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import com.darkstore.depot.producer.ShutDownProducer;
import com.darkstore.depot.producer.model.ShutDownInfo;
import com.darkstore.depot.producer.model.ShutDownMessage;
import com.darkstore.depot.repository.DepotRepository;
import com.darkstore.depot.service.DepotService;
import com.darkstore.depot.service.ShutDownService;
import com.darkstore.depot.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.darkstore.depot.model.response.DepotRestResponseCode.*;

@Service
public class ShutDownServiceImpl implements ShutDownService {
    private final DepotService depotService;
    private final DepotRepository depotRepository;
    private final ShutDownProducer shutDownProducer;
    private final StockService stockService;
    private final DistributedLocker locker;

    @Autowired
    public ShutDownServiceImpl(DepotService depotService, DepotRepository depotRepository, ShutDownProducer shutDownProducer, StockService stockService, DistributedLocker locker) {
        this.depotService = depotService;
        this.depotRepository = depotRepository;
        this.shutDownProducer = shutDownProducer;
        this.stockService = stockService;
        this.locker = locker;
    }

    @Override
    public void shutDownDepot(ShutDownDepotRequestDto request) {
        String key = "depot-api-shut-down-depot-" + request.getDepotName();
        LockExecutionResult<String> result = locker.lock(key, CommonProperties.REDIS_LOCK_ACQUIRED_SECONDS, CommonProperties.REDIS_LOCK_TIMEOUT_SECONDS, () -> {
            Depot depot = depotService.getDepot(request.getDepotName());
            Optional<Depot> optionalDepot = depotRepository.findByType(DepotTypeEnum.MAIN);
            checkShutDownRequest(depot, optionalDepot);
            Depot mainDepot = optionalDepot.get();
            List<Stock> stockList = stockService.findByDepotNameAndNumberOfStock(request.getDepotName());
            ShutDownMessage shutDownMessage = new ShutDownMessage();
            List<ShutDownInfo> shutDownInfoList = new ArrayList<>();
            for (Stock stock : stockList) {
                shutDownInfoList.add(mapToShutDownInfo(request, mainDepot, stock));
            }
            shutDownMessage.setShutDownInfoList(shutDownInfoList);
            shutDownProducer.sendMessage(shutDownMessage);
            depotRepository.updateStatusAndUpdatedDateByDepotName(DepotStatusEnum.CLOSED, LocalDateTime.now(), depot.getDepotName());
            return null;
        });
        if (result.hasException())
            throw (RestException) result.exception;
    }

    private static ShutDownInfo mapToShutDownInfo(ShutDownDepotRequestDto request, Depot mainDepot, Stock stock) {
        ShutDownInfo shutDownInfo = new ShutDownInfo();
        shutDownInfo.setTo(mainDepot.getDepotName());
        shutDownInfo.setFrom(request.getDepotName());
        shutDownInfo.setProductName(stock.getProductName());
        shutDownInfo.setNumberOfStock(stock.getNumberOfStock());
        return shutDownInfo;
    }

    private void checkShutDownRequest(Depot depot, Optional<Depot> optionalDepot){
        if (DepotTypeEnum.MAIN.equals(depot.getType())) {
            throw new RestException(DPT_MAIN_DEPOT_CANNOT_BE_CLOSED);
        }
        if (DepotStatusEnum.CLOSED.equals(depot.getStatus())) {
            throw new RestException(DPT_DEPOT_ALREADY_CLOSED);
        }
        if (optionalDepot.isEmpty())
            throw new RestException(DPT_MAIN_DEPOT_DOESNT_EXIST);
    }

}
