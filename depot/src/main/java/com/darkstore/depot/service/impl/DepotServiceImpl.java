package com.darkstore.depot.service.impl;

import com.darkstore.depot.common.exception.RestException;
import com.darkstore.depot.mapper.DepotMapper;
import com.darkstore.depot.model.dto.CreateDepotRequestDto;
import com.darkstore.depot.model.dto.DepotInfoResponseDto;
import com.darkstore.depot.model.dto.ShutDownDepotRequestDto;
import com.darkstore.depot.model.entity.Depot;
import com.darkstore.depot.model.enums.DepotTypeEnum;
import com.darkstore.depot.model.response.DepotRestResponseCode;
import com.darkstore.depot.producer.ShutDownProducer;
import com.darkstore.depot.repository.DepotRepository;
import com.darkstore.depot.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.darkstore.depot.common.response.model.RestResponseCode.VALIDATION_ERROR;

@Service
public class DepotServiceImpl implements DepotService {

    private final DepotRepository depotRepository;
    private final DepotMapper depotMapper;
    private final ShutDownProducer shutDownProducer;

    @Autowired
    public DepotServiceImpl(DepotRepository depotRepository, DepotMapper depotMapper, ShutDownProducer shutDownProducer) {
        this.depotRepository = depotRepository;
        this.depotMapper = depotMapper;
        this.shutDownProducer = shutDownProducer;
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

    @Override
    public Depot getDepot(String depotName) {
        Optional<Depot> optionalDepot = depotRepository.findByDepotName(depotName);
        if (optionalDepot.isEmpty())
            throw new RestException(DepotRestResponseCode.DPT_DEPOT_DOESNT_EXIST);
        return optionalDepot.get();
    }

    @Override
    public DepotInfoResponseDto getDepotInfo(String depotName) {
        return depotMapper.mapDepotToDepotInfo(getDepot(depotName));
    }
}
