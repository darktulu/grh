package com.bull.grh.domaine.validator.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bull.grh.domaine.Field;
import com.bull.grh.domaine.validator.UniqueEntry;

public class UniqueEntryValidator implements ConstraintValidator<UniqueEntry, Field> {

    @PersistenceContext
    private EntityManager em;

    private String formType;
    private String nom;

    @Override
    public void initialize(UniqueEntry constraintAnnotation) {
	nom = constraintAnnotation.nom();
	formType = constraintAnnotation.formType();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isValid(Field value, ConstraintValidatorContext context) {

	try {
	    Boolean isCreating = value.getValue() == null;
	    if (isCreating) {
		Query queryCount = em.createQuery(String.format(
			"select nom from RefField where %s = :unique and %s = :form ", nom, formType));
		queryCount.setParameter("unique", value.getValue());
		queryCount.setParameter("form", value.getDynamicForm());
		List<String> list = queryCount.getResultList();
		if (list.size() >= 1) {
		    for (String s : list) {
			if (s.equalsIgnoreCase(value.getValue())) {
			    return false;
			}
		    }
		}
		return true;
	    }
	} catch (final Exception ignore) {
	    ignore.printStackTrace();
	}
	return true;
    }

}
