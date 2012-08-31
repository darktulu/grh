package com.bull.grh.domaine.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.bull.grh.domaine.validator.FieldMatch;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	
    private String firstFieldName;
    private String secondFieldName;

	public void initialize(FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
	}

	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
			System.out.println("------------ First "+firstObj+" Second"+secondObj);
			return firstObj == null && secondObj == null || firstObj != null
					&& firstObj.equals(secondObj);
		} catch (final Exception ignore) {
			ignore.printStackTrace();
		}
		return true;
	}
}
