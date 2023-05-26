package com.darkstore.depot.service.impl;

import com.darkstore.depot.common.exception.RestException;
import com.darkstore.depot.mapper.DepotMapper;
import com.darkstore.depot.model.dto.CreateDepotRequestDto;
import com.darkstore.depot.model.entity.Depot;
import com.darkstore.depot.model.enums.DepotStatusEnum;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import com.darkstore.depot.model.response.DepotRestResponseCode;
import com.darkstore.depot.repository.DepotRepository;
import com.darkstore.depot.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepotServiceImpl implements DepotService {

    private final DepotRepository depotRepository;
    private final DepotMapper depotMapper;

    @Autowired
    public DepotServiceImpl(DepotRepository depotRepository, DepotMapper depotMapper) {
        this.depotRepository = depotRepository;
        this.depotMapper = depotMapper;
    }

    @Override
    public void createDepot(CreateDepotRequestDto requestDto) {
        if (requestDto.getType().equals(DepotTypeEnum.MAIN) && depotRepository.countByType(requestDto.getType()) > 0)
            throw new RestException(DepotRestResponseCode.DPT_MAIN_DEPOT_CANNOT_BE_MORE_THAN_ONE);
        depotRepository.save(depotMapper.mapCreateDepotRequestDtoToDepot(requestDto));
    }

    @Override
    public long countByDepotName(String depotName) {
        return depotRepository.countByDepotName(depotName);
    }

    @Override
    public Optional<Depot> findByDepotName(String depotName) {
        return depotRepository.findByDepotName(depotName);
    }
}
