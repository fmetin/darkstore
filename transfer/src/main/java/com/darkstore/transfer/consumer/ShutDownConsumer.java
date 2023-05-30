package com.darkstore.transfer.consumer;

import com.darkstore.transfer.consumer.model.ShutDownInfo;
import com.darkstore.transfer.consumer.model.ShutDownMessage;
import com.darkstore.transfer.mapper.TransferMapper;
import com.darkstore.transfer.model.dto.ApproveTransferRequestDto;
import com.darkstore.transfer.model.dto.TransferResponseDto;
import com.darkstore.transfer.model.enums.TransferStatusEnum;
import com.darkstore.transfer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShutDownConsumer {
    private final TransferService transferService;
    private final TransferMapper transferMapper;

    @Autowired
    public ShutDownConsumer(TransferService transferService, TransferMapper transferMapper) {
        this.transferService = transferService;
        this.transferMapper = transferMapper;
    }

    @RabbitListener(queues = {"${rabbitmq.shutDownQueue.name}"})
    public void consume(ShutDownMessage message){
        log.info(String.format("Received JSON message -> %s", message.toString()));
        for (ShutDownInfo shutDownInfo : message.getShutDownInfoList()) {
            try {
                TransferResponseDto transfer = transferService.transfer(transferMapper.mapTransferRequestDto(shutDownInfo));
                ApproveTransferRequestDto requestDto = new ApproveTransferRequestDto();
                requestDto.setStatus(TransferStatusEnum.COMPLETED);
                requestDto.setTransferId(transfer.getTransferId());
                transferService.approveTransfer(requestDto);
            } catch (Exception e) {
                log.error("method:shutDownConsume details: ",e);
            }

        }

    }
}
