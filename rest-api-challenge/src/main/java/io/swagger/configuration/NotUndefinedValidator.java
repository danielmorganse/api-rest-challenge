package io.swagger.configuration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.openapitools.jackson.nullable.JsonNullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class NotUndefinedValidator implements ConstraintValidator<NotUndefined, Object> {

    private static final Logger log = LoggerFactory.getLogger(NotUndefinedValidator.class);

    @Override
    public void initialize(NotUndefined constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object addressInformation, ConstraintValidatorContext context) {
        Class<?> objClass = addressInformation.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(JsonNullable.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(addressInformation);
                    if (value.equals(JsonNullable.undefined())) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(field.getName() + " cannot be undefined")
                                .addConstraintViolation();
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    log.warn("", e);
                }
            }
        }
        return true;
    }
}