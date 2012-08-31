package com.bull.grh.domaine.validator.impl;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

import com.bull.grh.domaine.validator.Interval;

public class IntervalValidator implements ConstraintValidator<Interval, Object> {
	
    private String minPropertyName;
    private String maxPropertyName;

	public void initialize(Interval constraintAnnotation) {
        minPropertyName = constraintAnnotation.minProperty();
        maxPropertyName = constraintAnnotation.maxProperty();
	}

	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		try {
            Object minValue = PropertyUtils.getProperty(value, minPropertyName);
            Object maxValue = PropertyUtils.getProperty(value, maxPropertyName);

            if(minValue != null && maxValue != null) {
                Long typedMin = 0l;
                Long typedMax = 0l;

                if(minValue instanceof Date) {
                    typedMin = ((Date)minValue).getTime();
                    typedMax = ((Date)maxValue).getTime();
                } else if(minValue instanceof Double) {
                    typedMin = ((Double)minValue).longValue();
                    typedMax = ((Double)maxValue).longValue();
                } else if(minValue instanceof Integer) {
                    typedMin = ((Integer)minValue).longValue();
                    typedMax = ((Integer)maxValue).longValue();
                }

                if(typedMin > typedMax)
                    return false;
                else
                    return true;
                
            } else
                return true;
		} catch (final Exception ignore) {
			ignore.printStackTrace();
		}
		return true;
	}
}
