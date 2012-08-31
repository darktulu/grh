package com.bull.grh.domaine.types;

public enum DecisionEntretien {

    OK("Ok"),
    PENDING("Pending"),
    REJECTED("Refused"),
    RECALL("Recall");

    String value;

    private DecisionEntretien(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }

}
