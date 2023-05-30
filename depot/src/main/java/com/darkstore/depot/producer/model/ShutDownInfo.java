package com.darkstore.depot.producer.model;

import com.darkstore.depot.model.enums.TransferTypeEnum;
import lombok.Data;

@Data
public class ShutDownInfo {
    private String from;
    private String to;
    private String productName;
    private long numberOfStock;
    private TransferTypeEnum transferType = TransferTypeEnum.DEPOT_SHUTDOWN_TRANSFER;
}
