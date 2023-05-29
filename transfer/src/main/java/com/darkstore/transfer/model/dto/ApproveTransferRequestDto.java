package com.darkstore.transfer.model.dto;

import com.darkstore.transfer.model.enums.TransferStatusEnum;
import lombok.Data;

@Data
public class ApproveTransferRequestDto {
    private long transferId;
    private TransferStatusEnum status;
}
