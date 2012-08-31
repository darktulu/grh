package com.bull.grh.domaine.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bull.grh.domaine.validator.impl.IntervalValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = IntervalValidator.class)
@Documented
public @interface Interval {
	String message() default "{com.bull.grh.business.validator.Interval}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	String minProperty();
	
	String maxProperty();
	
	String[] params() default {};
	
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		Interval[] value();
	}
}
