package com.darkstore.depot.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateStockListRequestDto {
    @NotNull
    @NotEmpty
    @Valid
    private List<UpdateStockRequestDto> stockList;
}
