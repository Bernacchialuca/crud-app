package com.demo.crudapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotZeroValidator.class)
public @interface NotZero {

    String message() default "El valor no puede ser cero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
