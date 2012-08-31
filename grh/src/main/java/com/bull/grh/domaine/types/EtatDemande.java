package com.bull.grh.domaine.types;

import java.io.Serializable;

public enum EtatDemande implements Serializable {

    NEW("New"),
    SENT("Sent"),
    REJECTED("Rejected"),
    APPROVED("Approved"),
    COMPLETED("Completed"),
    DELETED("Deleted");

    String value;

    private EtatDemande(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }
}
