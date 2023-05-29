package com.darkstore.transfer.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TransferRequestDto {

    @NotNull
    @Size(min = 1, max = 255)
    private String from;
    @NotNull
    @Size(min = 1, max = 255)
    private String to;
    @NotNull
    @Size(min = 1, max = 255)
    private String productName;
    @Min(1)
    private long numberOfStock;
}
