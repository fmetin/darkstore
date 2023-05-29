package com.darkstore.transfer.service;

import com.darkstore.transfer.model.dto.ApproveTransferRequestDto;
import com.darkstore.transfer.model.dto.TransferRequestDto;
import com.darkstore.transfer.model.dto.TransferResponseDto;

public interface TransferService {
    TransferResponseDto transfer(TransferRequestDto request);

    void approveTransfer(ApproveTransferRequestDto request);
}
