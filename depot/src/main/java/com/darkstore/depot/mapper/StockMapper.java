package com.darkstore.depot.mapper;


import com.darkstore.depot.common.util.LocalDateTimeUtil;
import com.darkstore.depot.model.dto.CreateStockRequestDto;
import com.darkstore.depot.model.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class StockMapper {

    @Autowired
    protected LocalDateTimeUtil localDateTimeUtil;

    @Mapping(target = "createdDate", expression = "java(localDateTimeUtil.now())")
    @Mapping(target = "updatedDate", expression = "java(localDateTimeUtil.now())")
    public abstract Stock mapCreateStockRequestDtoToStock(CreateStockRequestDto requestDto);

}
