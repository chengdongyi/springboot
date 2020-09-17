package com.example.annotation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FixedValueValidator implements ConstraintValidator<FixedValue, String> {

    private String[] params;

    @Override
    public void initialize(FixedValue fixedValue) {
        this.params = fixedValue.params();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isEmpty(value)) {
            return true;
        }

        for (String param : this.params) {
            if (param.equals(value)) {
                return true;
            }
        }
        return false;
    }

}