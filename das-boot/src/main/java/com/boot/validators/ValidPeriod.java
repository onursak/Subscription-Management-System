package com.boot.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
//Note: We use here already a validator which we will add in a sec too
@Constraint(validatedBy = PeriodValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPeriod {
 // used to get later in the resource bundle the i18n text
 String message() default "Charging period cannot be higher than subscription period!";
 Class<?>[] groups() default {};
 Class<? extends Payload>[] payload() default {};
 // min value, we for now just a string
 String subscriptionPeriod();
 String chargingPeriod();
}
