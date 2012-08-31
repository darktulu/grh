package com.bull.grh.domaine.validator.impl;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bull.grh.domaine.Field;
import com.bull.grh.domaine.types.Regexp;
import com.bull.grh.domaine.validator.RegexValidation;

public class RegexValidationValidator implements
	ConstraintValidator<RegexValidation, Field> {

    @Override
    public void initialize(RegexValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(Field value, ConstraintValidatorContext arg1) {

	try {
	    Boolean isCreating = value.getValue() == null;
	    if (!isCreating) {
		Regexp test = value.getRefField().getRegExpr();
		if (Regexp.ANY == test || Regexp.BOOLEAN == test
			|| Regexp.DATE == test) {
		    return true;

		} else {
		    Pattern regex = java.util.regex.Pattern.compile(value
			    .getRefField().getRegExpr().getValue());
		    return regex.matcher(value.getValue()).matches();
		}
	    }
	} catch (final Exception ignore) {
	    // ignore
	}
	return true;
    }

}
