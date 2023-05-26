package com.darkstore.depot.mapper;


import com.darkstore.depot.common.util.LocalDateTimeUtil;
import com.darkstore.depot.model.dto.CreateProductRequestDto;
import com.darkstore.depot.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected LocalDateTimeUtil localDateTimeUtil;

    @Mapping(target = "createdDate", expression = "java(localDateTimeUtil.now())")
    @Mapping(target = "updatedDate", expression = "java(localDateTimeUtil.now())")
    public abstract Product mapCreateProductRequestDtoToProduct(CreateProductRequestDto requestDto);

}
