package com.bull.grh.domaine.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bull.grh.domaine.validator.impl.ExclusiveFieldValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ExclusiveFieldValidator.class)
@Documented
public @interface ExclusiveField {
	String message() default "{com.bull.grh.business.validator.ExclusiveField}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	String first();
	
	String second();
	
	String[] params() default {};
	
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		ExclusiveField[] value();
	}
}
