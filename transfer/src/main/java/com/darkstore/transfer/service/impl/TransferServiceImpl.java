package com.darkstore.transfer.service.impl;

import com.darkstore.transfer.client.depot.DepotClient;
import com.darkstore.transfer.client.depot.model.dto.stock.info.StockInfoResponseDto;
import com.darkstore.transfer.client.depot.model.enums.DepotStatusEnum;
import com.darkstore.transfer.client.depot.model.enums.DepotTypeEnum;
import com.darkstore.transfer.common.exception.RestException;
import com.darkstore.transfer.common.properties.CommonProperties;
import com.darkstore.transfer.common.util.DistanceCalculator;
import com.darkstore.transfer.locker.DistributedLocker;
import com.darkstore.transfer.locker.LockExecutionResult;
import com.darkstore.transfer.mapper.TransferMapper;
import com.darkstore.transfer.model.dto.ApproveTransferRequestDto;
import com.darkstore.transfer.model.dto.TransferRequestDto;
import com.darkstore.transfer.model.dto.TransferResponseDto;
import com.darkstore.transfer.model.entity.Transfer;
import com.darkstore.transfer.model.enums.TransferStatusEnum;
import com.darkstore.transfer.model.enums.TransferTypeEnum;
import com.darkstore.transfer.repository.TransferRepository;
import com.darkstore.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.darkstore.transfer.common.properties.CommonProperties.INNER_CITY_TRANSFER_MAX_DISTANCE;
import static com.darkstore.transfer.common.response.model.RestResponseCode.VALIDATION_ERROR;
import static com.darkstore.transfer.model.response.TransferRestResponseCode.*;

@Service
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;
    private final DistributedLocker locker;

    private final DepotClient depotClient;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository, TransferMapper transferMapper, DistributedLocker locker, DepotClient depotClient) {
        this.transferRepository = transferRepository;
        this.transferMapper = transferMapper;
        this.locker = locker;
        this.depotClient = depotClient;
    }

    @Override
    public TransferResponseDto transfer(TransferRequestDto request) {
        TransferResponseDto responseDto = new TransferResponseDto();
        Transfer transfer = transferRepository.saveAndFlush(transferMapper.mapTransferRequestDtoToTransfer(request));
        String key = "transfer-api-transfer-" + request.getFrom() + "-" + request.getTo() + "-" + request.getProductName();
        LockExecutionResult<TransferResponseDto> result = locker.lock(key, CommonProperties.REDIS_LOCK_ACQUIRED_SECONDS, CommonProperties.REDIS_LOCK_TIMEOUT_SECONDS, () -> {
            StockInfoResponseDto stockInfoResponseDto = depotClient.stockInfo(transferMapper.mapTransferRequestDtoToStockInfoRequestDto(request));
            checkTransfer(stockInfoResponseDto, request.getTransferType());
            depotClient.updateStock(transferMapper.mapFromDepotRequest(request));
            responseDto.setTransferId(transfer.getId());
            return responseDto;
        });
        if (result.hasException()) {
            transferRepository.updateStatusAndUpdatedDateById(TransferStatusEnum.FAILED, LocalDateTime.now(), transfer.getId());
            throw (RestException) result.exception;
        }
        return responseDto;
    }

    @Override
    public void approveTransfer(ApproveTransferRequestDto request) {
        if (request.getStatus().equals(TransferStatusEnum.IN_PROGRESS))
            throw new RestException(TRN_TRANSFER_RECORD_DOESNT_EXIST);
        Optional<Transfer> optionalTransfer = transferRepository.findById(request.getTransferId());
        if (optionalTransfer.isEmpty())
            throw new RestException(TRN_TRANSFER_RECORD_DOESNT_EXIST);
        Transfer transfer = optionalTransfer.get();
        if (!transfer.getStatus().equals(TransferStatusEnum.IN_PROGRESS))
            throw new RestException(TRN_TRANSFER_RECORD_DOESNT_EXIST);
        String key = "transfer-api-approve-transfer-" + request.getTransferId();
        LockExecutionResult<String> result = locker.lock(key, CommonProperties.REDIS_LOCK_ACQUIRED_SECONDS, CommonProperties.REDIS_LOCK_TIMEOUT_SECONDS, () -> {
            if (request.getStatus().equals(TransferStatusEnum.COMPLETED))
                depotClient.updateStock(transferMapper.mapToDepotRequest(transfer));
            else
                depotClient.updateStock(transferMapper.mapFromDepotRequest(transfer));
            transferRepository.updateStatusAndUpdatedDateById(request.getStatus(), LocalDateTime.now(), transfer.getId());
            return null;
        });
        if (result.hasException()) {
            depotClient.updateStock(transferMapper.mapFromDepotRequest(transfer));
            transferRepository.updateStatusAndUpdatedDateById(TransferStatusEnum.FAILED, LocalDateTime.now(), transfer.getId());
            throw (RestException) result.exception;
        }
    }

    private static void checkTransfer(StockInfoResponseDto stockInfoResponseDto, TransferTypeEnum transferTypeEnum) {
        if (!TransferTypeEnum.DEPOT_SHUTDOWN_TRANSFER.equals(transferTypeEnum) &&
                (!DepotStatusEnum.OPEN.equals(stockInfoResponseDto.getFrom().getStatus()) ||
                        !DepotStatusEnum.OPEN.equals(stockInfoResponseDto.getTo().getStatus())))
            throw new RestException(TRN_DEPOT_CLOSED);
        if (!stockInfoResponseDto.isHasStock())
            throw new RestException(TRN_DEPOT_HAS_NOT_ENOUGH_STOCK);
        if (!TransferTypeEnum.DEPOT_SHUTDOWN_TRANSFER.equals(transferTypeEnum) &&
                !stockInfoResponseDto.getFrom().getCity().equals(stockInfoResponseDto.getTo().getCity()) &&
                !stockInfoResponseDto.getFrom().getType().equals(DepotTypeEnum.MAIN))
            throw new RestException(TRN_INTERCITY_TRANSFERS_CAN_ONLY_BE_MADE_FROM_THE_MAIN_DEPOT);

        if (stockInfoResponseDto.getTo().getType().equals(DepotTypeEnum.DISTRIBUTION_CENTER) &&
                stockInfoResponseDto.getFrom().getType().equals(DepotTypeEnum.DISTRIBUTION_CENTER) &&
                DistanceCalculator.distanceBetweenCoordinates(
                        Double.parseDouble(stockInfoResponseDto.getFrom().getLatitude()),
                        Double.parseDouble(stockInfoResponseDto.getFrom().getLongitude()),
                        Double.parseDouble(stockInfoResponseDto.getTo().getLatitude()),
                        Double.parseDouble(stockInfoResponseDto.getTo().getLongitude())) >= INNER_CITY_TRANSFER_MAX_DISTANCE)
            throw new RestException(TRN_INNER_CITY_TRANSFER_DISTANCE_ERROR);

    }
}
