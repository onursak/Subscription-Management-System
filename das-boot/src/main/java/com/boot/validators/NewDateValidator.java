package com.boot.validators;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NewDateValidator implements ConstraintValidator<ValidDate, Object> {
    
    private String startDate;
    private String endDate;
 
    private ValidDate constraintAnnotation;
    
    @Override
    public void initialize(ValidDate constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
        startDate = constraintAnnotation.startDate();
        endDate = constraintAnnotation.endDate();
    }
    
    @Override
    public boolean isValid(Object requestObject, ConstraintValidatorContext context) {
			LocalDateTime startDate , endDate;
			try {
				startDate = (LocalDateTime) requestObject.getClass().getMethod("getStartDate").invoke(requestObject);
				endDate = (LocalDateTime) requestObject.getClass().getMethod("getEndDate").invoke(requestObject);
				return startDate.isBefore(endDate);
			}
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
				return false;
			}
				
    }
}