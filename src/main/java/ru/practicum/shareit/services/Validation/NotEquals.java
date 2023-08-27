package ru.practicum.shareit.services.Validation;

import ru.practicum.shareit.services.Validation.NotEqualsValidator;

import javax.validation.Constraint;
import javax.validation.constraints.Past;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEqualsValidator.class)
@Past
public @interface NotEquals
{
    String message() default "Date must not be equals {value}";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    String value() default "1895-12-28";
}