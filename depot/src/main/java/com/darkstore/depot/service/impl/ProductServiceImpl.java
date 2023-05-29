package com.darkstore.depot.service.impl;

import com.darkstore.depot.common.exception.RestException;
import com.darkstore.depot.mapper.ProductMapper;
import com.darkstore.depot.model.dto.CreateProductRequestDto;
import com.darkstore.depot.model.entity.Product;
import com.darkstore.depot.model.response.DepotRestResponseCode;
import com.darkstore.depot.repository.ProductRepository;
import com.darkstore.depot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void createProduct(CreateProductRequestDto request) {
        productRepository.save(productMapper.mapCreateProductRequestDtoToProduct(request));
    }

    @Override
    public long countByName(String name) {
        return productRepository.countByName(name);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product getProduct(String productName) {
        Optional<Product> optionalProduct = productRepository.findByName(productName);
        if (optionalProduct.isEmpty())
            throw new RestException(DepotRestResponseCode.DPT_PRODUCT_DOESNT_EXIST);
        return optionalProduct.get();
    }
}
