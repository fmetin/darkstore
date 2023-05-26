package com.darkstore.depot.annotation;


import com.darkstore.depot.service.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueProductValidator implements ConstraintValidator<UniqueProduct, String> {
    private final ProductService productService;

    @Autowired
    public UniqueProductValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return productService.countByName(name) <= 0;
    }
}
