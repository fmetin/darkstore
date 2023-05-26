package com.darkstore.depot.annotation;


import com.darkstore.depot.service.DepotService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueDepotValidator implements ConstraintValidator<UniqueDepot, String> {
    private final DepotService depotService;

    @Autowired
    public UniqueDepotValidator(DepotService depotService) {
        this.depotService = depotService;
    }

    @Override
    public boolean isValid(String depotName, ConstraintValidatorContext constraintValidatorContext) {
        return depotService.countByDepotName(depotName) <= 0;
    }
}
