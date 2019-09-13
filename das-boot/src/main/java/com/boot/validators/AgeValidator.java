package com.boot.validators;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {
    
    private String startDate;
    private String endDate;
 
    private ValidAge constraintAnnotation;
    
    @Override
    public void initialize(ValidAge constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
    
    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
			try {
				if(birthdate.plusYears(18).isAfter(LocalDate.now())) {
					return false;
				}
				else {
					return true;
				}
			}
			catch (Exception e) {
				return false;
			}
				
    }
}
