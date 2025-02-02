package io.swagger.configuration;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotUndefinedValidator.class)
public @interface NotUndefined {
    String message() default "field cannot be undefined";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
