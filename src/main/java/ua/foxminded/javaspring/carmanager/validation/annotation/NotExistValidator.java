package ua.foxminded.javaspring.carmanager.validation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ua.foxminded.javaspring.carmanager.service.CarService;

@RequiredArgsConstructor
public class NotExistValidator implements ConstraintValidator<NotExist, String> {

    private final CarService service;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return !service.existsById(id);
    }
}
