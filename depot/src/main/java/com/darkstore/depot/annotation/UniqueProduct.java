package com.darkstore.depot.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.darkstore.depot.model.response.DepotRestResponseMessage.MSG_DPT_UNIQUE_PRODUCT;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {UniqueProductValidator.class}
)
public @interface UniqueProduct {

    String message() default MSG_DPT_UNIQUE_PRODUCT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
