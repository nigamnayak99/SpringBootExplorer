package com.nigam.springbootexplorer.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint( validatedBy = {JobTitleValidator.class})
public @interface JobTitleValidation {

    String message() default "Employee Job Should be Software Engineer or Manager";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
