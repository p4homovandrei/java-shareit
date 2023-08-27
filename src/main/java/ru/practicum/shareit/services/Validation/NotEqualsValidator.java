package ru.practicum.shareit.services.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;


public class NotEqualsValidator implements ConstraintValidator<NotEquals, LocalDate> {
    private LocalDate minimumDate;

    @Override
    public void initialize(NotEquals constraintAnnotation) {

        minimumDate = LocalDate.parse(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value == null || !value.isBefore(minimumDate);
    }
}