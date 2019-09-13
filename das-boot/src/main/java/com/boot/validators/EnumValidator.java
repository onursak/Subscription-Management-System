package com.boot.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, Object> {
    
    private ValidEnum constraintAnnotation;
    
    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
    
    @Override
    public boolean isValid(Object enumObject, ConstraintValidatorContext context) {
			boolean result = false;
    		Object[] enumValues = enumObject.getClass().getEnumConstants();
			if(enumValues != null)
	        {
	            for(Object enumValue:enumValues)
	            {
	                if(enumObject.toString().toUpperCase().equals(enumValue.toString().toUpperCase()))
	                {
	                    result = true; 
	                    break;
	                }
	            }
	        }
			return result;
    }
}