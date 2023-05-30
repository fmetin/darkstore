package com.darkstore.depot.controller;

import com.darkstore.depot.common.response.model.RestResponse;
import com.darkstore.depot.model.dto.CreateDepotRequestDto;
import com.darkstore.depot.model.dto.ShutDownDepotRequestDto;
import com.darkstore.depot.service.DepotService;
import com.darkstore.depot.service.ShutDownService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepotController {

    private final DepotService depotService;
    private final ShutDownService shutDownService;

    @Autowired
    public DepotController(DepotService depotService, ShutDownService shutDownService) {
        this.depotService = depotService;
        this.shutDownService = shutDownService;
    }

    @PostMapping("/v1/create-depot")
    public ResponseEntity<Object> createDepot(@Valid @RequestBody CreateDepotRequestDto request) {
        depotService.createDepot(request);
        return ResponseEntity.ok(new RestResponse<>());
    }

    @PostMapping("/v1/shut-down-depot")
    public ResponseEntity<Object> shutDownDepot(@Valid @RequestBody ShutDownDepotRequestDto request) {
        shutDownService.shutDownDepot(request);
        return ResponseEntity.ok(new RestResponse<>());
    }

}
