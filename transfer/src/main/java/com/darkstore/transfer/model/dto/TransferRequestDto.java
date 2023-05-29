package com.darkstore.transfer.model.dto;

import lombok.Data;

@Data
public class TransferRequestDto {
    private String from;
    private String to;
    private String productName;
    private long numberOfStock;
}
