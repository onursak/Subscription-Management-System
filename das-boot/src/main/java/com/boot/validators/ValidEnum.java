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
@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnum {
// used to get later in the resource bundle the i18n text
String message() default "There is no enum type like this!";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
// min value, we for now just a string
}
