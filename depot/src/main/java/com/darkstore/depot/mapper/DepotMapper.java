package com.darkstore.depot.mapper;


import com.darkstore.depot.common.util.LocalDateTimeUtil;
import com.darkstore.depot.model.dto.CreateDepotRequestDto;
import com.darkstore.depot.model.dto.DepotInfoResponseDto;
import com.darkstore.depot.model.entity.Depot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class DepotMapper {

    @Autowired
    protected LocalDateTimeUtil localDateTimeUtil;

    @Mapping(target = "createdDate", expression = "java(localDateTimeUtil.now())")
    @Mapping(target = "updatedDate", expression = "java(localDateTimeUtil.now())")
    public abstract Depot mapCreateDepotRequestDtoToDepot(CreateDepotRequestDto requestDto);

    public abstract DepotInfoResponseDto mapDepotToDepotInfo(Depot depot);

}
