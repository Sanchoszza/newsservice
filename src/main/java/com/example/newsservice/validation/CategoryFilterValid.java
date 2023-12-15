package com.example.newsservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryFilterValid {

    String message() default "Polya pagination have to be field in!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
