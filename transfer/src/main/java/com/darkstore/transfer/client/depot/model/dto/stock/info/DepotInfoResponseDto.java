package com.darkstore.transfer.client.depot.model.dto.stock.info;


import com.darkstore.transfer.client.depot.model.enums.DepotStatusEnum;
import com.darkstore.transfer.client.depot.model.enums.DepotTypeEnum;
import lombok.Data;

@Data
public class DepotInfoResponseDto {
    private String depotName;
    private DepotTypeEnum type;
    private String city;
    private String latitude;
    private String longitude;
    private DepotStatusEnum status;
    private String costCenter;

}
