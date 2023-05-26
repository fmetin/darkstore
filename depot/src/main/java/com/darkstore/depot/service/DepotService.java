package com.darkstore.depot.service;

import com.darkstore.depot.model.dto.CreateDepotRequestDto;

public interface DepotService {

    void createDepot(CreateDepotRequestDto requestDto);
}
