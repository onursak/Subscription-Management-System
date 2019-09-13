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
@Constraint(validatedBy = NewDateValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {
 // used to get later in the resource bundle the i18n text
 String message() default "Dates are unacceptable!";
 Class<?>[] groups() default {};
 Class<? extends Payload>[] payload() default {};
 // min value, we for now just a string
 String startDate();
 String endDate();
}