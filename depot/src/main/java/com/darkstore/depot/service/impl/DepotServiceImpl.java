package com.darkstore.depot.service.impl;

import com.darkstore.depot.mapper.DepotMapper;
import com.darkstore.depot.model.dto.CreateDepotRequestDto;
import com.darkstore.depot.repository.DepotRepository;
import com.darkstore.depot.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        depotRepository.save(depotMapper.mapCreateDepotRequestDtoToDepot(requestDto));
    }
}
