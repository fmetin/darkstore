package com.darkstore.depot.service;

import com.darkstore.depot.model.dto.CreateDepotRequestDto;
import com.darkstore.depot.model.dto.DepotInfoResponseDto;
import com.darkstore.depot.model.dto.ShutDownDepotRequestDto;
import com.darkstore.depot.model.entity.Depot;

import java.util.Optional;

public interface DepotService {

    void createDepot(CreateDepotRequestDto requestDto);
    long countByDepotName(String depotName);
    Optional<Depot> findByDepotName(String depotName);
    Depot getDepot(String depotName);
    DepotInfoResponseDto getDepotInfo(String depotName);
}
