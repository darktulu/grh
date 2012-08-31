package com.bull.grh.domaine.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bull.grh.domaine.validator.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {
	private final String PASSWORD_REGEXP = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	public void initialize(Password constraintAnnotation) { }

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.trim().equals("")) return true;
		return value.matches(PASSWORD_REGEXP);
	}
}
