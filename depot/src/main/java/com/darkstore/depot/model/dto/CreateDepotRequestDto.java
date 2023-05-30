package com.darkstore.depot.model.dto;

import com.darkstore.depot.annotation.UniqueDepot;
import com.darkstore.depot.model.enums.DepotStatusEnum;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.darkstore.depot.model.response.DepotRestResponseMessage.*;

@Data
public class CreateDepotRequestDto {

    @NotNull(message = MSG_VALIDATION_CONSTRAINT_DEPOT_NAME_NOTNULL)
    @UniqueDepot
    @Size(min = 1, max = 255, message = MSG_VALIDATION_CONSTRAINT_DEPOT_NAME_SIZE)
    private String depotName;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_TYPE_NOTNULL)
    private DepotTypeEnum type;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_CITY_NOTNULL)
    private String city;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_LATITUDE_NOTNULL)
//    @Pattern(regexp="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$", message = MSG_VALIDATION_CONSTRAINT_LATITUDE_PATTERN)
    private String latitude;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_LONGITUDE_NOTNULL)
//    @Pattern(regexp="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$", message = MSG_VALIDATION_CONSTRAINT_LONGITUDE_PATTERN)
    private String longitude;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_STATUS_NOTNULL)
    private DepotStatusEnum status;
    @NotNull(message = MSG_VALIDATION_CONSTRAINT_COST_CENTER_NOTNULL)
    @Size(min = 1, max = 255, message = MSG_VALIDATION_CONSTRAINT_COST_CENTER_SIZE)
    private String costCenter;

}
