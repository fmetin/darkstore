package com.darkstore.transfer.mapper;

import com.darkstore.transfer.client.depot.model.dto.stock.info.StockInfoRequestDto;
import com.darkstore.transfer.client.depot.model.dto.stock.update.UpdateStockRequestDto;
import com.darkstore.transfer.common.util.LocalDateTimeUtil;
import com.darkstore.transfer.consumer.model.ShutDownInfo;
import com.darkstore.transfer.model.dto.TransferRequestDto;
import com.darkstore.transfer.model.entity.Transfer;
import com.darkstore.transfer.model.enums.TransferStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TransferMapper {
    @Autowired
    protected LocalDateTimeUtil localDateTimeUtil;

    protected TransferStatusEnum transferStatusEnum;

    @Mapping(target = "createdDate", expression = "java(localDateTimeUtil.now())")
    @Mapping(target = "updatedDate", expression = "java(localDateTimeUtil.now())")
    @Mapping(target = "fromDepotName", source = "from")
    @Mapping(target = "toDepotName", source = "to")
    @Mapping(target = "status", expression = "java(transferStatusEnum.IN_PROGRESS)")
    public abstract Transfer mapTransferRequestDtoToTransfer(TransferRequestDto requestDto);

    public abstract StockInfoRequestDto mapTransferRequestDtoToStockInfoRequestDto(TransferRequestDto requestDto);

    @Mapping(target = "depotName", source = "from")
    @Mapping(target = "numberOfStock", expression = "java(requestDto.getNumberOfStock() * -1)")
    public abstract UpdateStockRequestDto mapFromDepotRequest(TransferRequestDto requestDto);

    @Mapping(target = "depotName", source = "fromDepotName")
    public abstract UpdateStockRequestDto mapFromDepotRequest(Transfer transfer);

    @Mapping(target = "depotName", source = "toDepotName")
    public abstract UpdateStockRequestDto mapToDepotRequest(Transfer transfer);

    public abstract TransferRequestDto mapTransferRequestDto(ShutDownInfo shutDownInfo);




}
