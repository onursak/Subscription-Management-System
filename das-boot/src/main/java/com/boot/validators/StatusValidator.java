package com.boot.validators;


import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.boot.enums.Status;

public class StatusValidator implements ConstraintValidator<ValidStatus, Object> {
    private ValidStatus constraintAnnotation;
    @Override
    public void initialize(ValidStatus constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
    
    @Override
    public boolean isValid(Object requestObject, ConstraintValidatorContext context) {
			LocalDateTime startDate , endDate;
			LocalDateTime now = LocalDateTime.now();
			Status status;
			try {
				startDate = (LocalDateTime) requestObject.getClass().getMethod("getStartDate").invoke(requestObject);
				endDate = (LocalDateTime) requestObject.getClass().getMethod("getEndDate").invoke(requestObject);
				status = (Status) requestObject.getClass().getMethod("getStatus").invoke(requestObject);
				if(status.toString().equals("ACTIVE") && startDate.isBefore(now) && endDate.isAfter(now)) {
					return true;
				}
				else if(status.toString().equals("TERMINATED") && endDate.isBefore(now)) {
					return true;
				}
				else if(status.toString().equals("SUSPENDED")) {
					if(startDate.isAfter(now) || (startDate.isBefore(now) && endDate.isAfter(now))) {
						return true;
					}
				}
				else {
					return false;
				}
			}
			catch (Exception e) {
				return false;
			}
			return false;	
    }
}