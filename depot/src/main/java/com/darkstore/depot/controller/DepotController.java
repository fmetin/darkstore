package com.darkstore.depot.controller;

import com.darkstore.depot.common.response.model.RestResponse;
import com.darkstore.depot.model.dto.CreateDepotRequestDto;
import com.darkstore.depot.service.DepotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/depot")
public class DepotController {

    private final DepotService depotService;

    @Autowired
    public DepotController(DepotService depotService) {
        this.depotService = depotService;
    }

    @PostMapping("/v1/create-depot")
    public ResponseEntity<Object> createDepot(@Valid @RequestBody CreateDepotRequestDto request) {
        depotService.createDepot(request);
        return ResponseEntity.ok(new RestResponse<>());
    }
}
