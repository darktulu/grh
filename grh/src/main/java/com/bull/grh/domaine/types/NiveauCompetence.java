package com.bull.grh.domaine.types;

public enum NiveauCompetence {

    EXCELLENT("Excellent"),
    GOOD("Bon"),
    AVERAGE("Moyen"),
    BASICS("Notions");

    String value;

    private NiveauCompetence(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }

}
