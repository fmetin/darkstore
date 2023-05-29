package com.darkstore.transfer.controller;

import com.darkstore.transfer.common.response.model.RestResponse;
import com.darkstore.transfer.model.dto.ApproveTransferRequestDto;
import com.darkstore.transfer.model.dto.TransferRequestDto;
import com.darkstore.transfer.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/v1/transfer")
    public ResponseEntity<Object> transfer(@Valid @RequestBody TransferRequestDto request) {
        transferService.transfer(request);
        return ResponseEntity.ok(new RestResponse<>());
    }

    @PostMapping("/v1/approve-transfer")
    public ResponseEntity<Object> approveTransfer(@Valid @RequestBody ApproveTransferRequestDto request) {
        transferService.approveTransfer(request);
        return ResponseEntity.ok(new RestResponse<>());
    }
}
