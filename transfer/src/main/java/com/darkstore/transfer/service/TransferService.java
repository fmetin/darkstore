package com.darkstore.transfer.service;

import com.darkstore.transfer.model.dto.ApproveTransferRequestDto;
import com.darkstore.transfer.model.dto.TransferRequestDto;

public interface TransferService {
    void transfer(TransferRequestDto request);

    void approveTransfer(ApproveTransferRequestDto request);
}
