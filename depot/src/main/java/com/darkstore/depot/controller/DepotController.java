package com.darkstore.depot.controller;

import com.darkstore.depot.common.response.model.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depot")
public class DepotController {

    @GetMapping("/v1/test")
    public ResponseEntity<Object> getCustomerStatistics() {
        return ResponseEntity.ok(new RestResponse<>());
    }
}
