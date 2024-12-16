package com.nigam.springbootexplorer.annotations;

import com.nigam.springbootexplorer.util.Constants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class JobTitleValidator implements ConstraintValidator<JobTitleValidation, String> {

    @Override
    public void initialize(JobTitleValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String jobTitle, ConstraintValidatorContext constraintValidatorContext) {
        return List.of(Constants.SOFTWARE_ENGINEER, Constants.MANAGER).contains(jobTitle);
    }
}
