package com.bull.grh.domaine.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bull.grh.domaine.validator.impl.RegexValidationValidator;


@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RegexValidationValidator.class)
public @interface RegexValidation {

    String message() default "{com.bull.grh.business.validator.RegexValidation}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
