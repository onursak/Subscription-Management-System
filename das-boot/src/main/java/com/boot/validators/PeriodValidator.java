package com.boot.validators;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodValidator implements ConstraintValidator<ValidPeriod, Object> {
    
    private String subscriptionPeriod;
    private String chargingPeriod;
 
    private ValidPeriod constraintAnnotation;
    
    @Override
    public void initialize(ValidPeriod constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
        subscriptionPeriod = constraintAnnotation.subscriptionPeriod();
        chargingPeriod = constraintAnnotation.chargingPeriod();
    }
    
    @Override
    public boolean isValid(Object requestObject, ConstraintValidatorContext context) {
			Integer subscriptionPeriod , chargingPeriod;
			try {
				subscriptionPeriod = (Integer) requestObject.getClass().getMethod("getSubscriptionPeriod").invoke(requestObject);
				chargingPeriod = (Integer) requestObject.getClass().getMethod("getChargingPeriod").invoke(requestObject);
				return (subscriptionPeriod>=chargingPeriod);
			} 
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
				return false;
			}
				
    }
}