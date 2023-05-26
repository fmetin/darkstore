package com.darkstore.depot.model.dto;

import com.darkstore.depot.model.enums.DepotStatus;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateDepotRequestDto {
    private String depotName;
    private DepotTypeEnum type;
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private DepotStatus status;
    private String costCenter;

}
