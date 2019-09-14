package com.boot.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PeriodValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPeriod {
 String message() default "Charging period cannot be higher than subscription period!";
 Class<?>[] groups() default {};
 Class<? extends Payload>[] payload() default {};
 String subscriptionPeriod();
 String chargingPeriod();
}
