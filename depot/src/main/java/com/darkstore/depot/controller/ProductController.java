package com.darkstore.depot.controller;

import com.darkstore.depot.common.response.model.RestResponse;
import com.darkstore.depot.model.dto.CreateProductRequestDto;
import com.darkstore.depot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/v1/create-product")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody CreateProductRequestDto request) {
        productService.createProduct(request);
        return ResponseEntity.ok(new RestResponse<>());
    }
}
