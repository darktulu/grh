package com.bull.grh.domaine.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.bull.grh.domaine.validator.impl.UniqueEntryValidator;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueEntryValidator.class)
@Documented
public @interface UniqueEntry {

    String message() default "{com.bull.grh.business.validator.UniqueEntry}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String nom();

    String formType();
}
