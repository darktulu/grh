package com.bull.grh.domaine.validator.impl;

import org.apache.commons.beanutils.BeanUtils;

import com.bull.grh.domaine.validator.ExclusiveField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExclusiveFieldValidator implements ConstraintValidator<ExclusiveField, Object> {
	
    private String firstFieldName;
    private String secondFieldName;

	public void initialize(ExclusiveField constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
	}

	public boolean isValid(final Object value, ConstraintValidatorContext context) {
        try {
            final String firstObj = BeanUtils.getProperty(value, firstFieldName);
            final String secondObj = BeanUtils.getProperty(value, secondFieldName);

            return ((firstObj.equals("") || null == firstObj) ^
                    (secondObj.equals("") || null == secondObj));
        } catch (final Exception ignore) {
            ignore.printStackTrace();
        }
		return false;
	}
}
