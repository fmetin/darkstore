package com.darkstore.transfer.model.dto;

import com.darkstore.transfer.model.enums.TransferStatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApproveTransferRequestDto {
    @Min(0)
    private long transferId;
    @NotNull
    private TransferStatusEnum status;
}
