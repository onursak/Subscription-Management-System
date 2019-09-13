package com.boot.validators;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.boot.enums.Status;

public class NameValidator implements ConstraintValidator<ValidName, String> {
    private ValidName constraintAnnotation;
    
    @Override
    public void initialize(ValidName constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
    
    @Override
    public boolean isValid(String requestObject, ConstraintValidatorContext context) {
			try {
				return !(requestObject.matches(".*\\d+.*"));
			}
			catch (Exception e) {
				return false;
			}
    }
}
