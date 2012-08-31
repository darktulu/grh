package com.bull.grh.domaine.types;

import java.io.Serializable;

public enum EtatEvaluation implements Serializable {

    NEW("New"),
    SENT("Sent"),
    APPROVED("Approved"),
    COMPLETED("Completed");

    String value;

    private EtatEvaluation(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }

}
