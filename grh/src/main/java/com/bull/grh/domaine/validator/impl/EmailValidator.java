package com.bull.grh.domaine.validator.impl;

import com.bull.grh.domaine.validator.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
    private final String EMAIL_REGEXP = "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})" + "^[\\w]+(\\.[\\w-]+)*@";

    public void initialize(Email constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.trim().equals("") || value.matches(EMAIL_REGEXP);
    }
}
