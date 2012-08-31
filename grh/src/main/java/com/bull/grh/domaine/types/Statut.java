package com.bull.grh.domaine.types;

import java.io.Serializable;

public enum Statut implements Serializable {

    ABSENT("Absent"),
    ACTIF("Actif"),
    DEMISSIONNAIRE("Démissionnaire"),
    INACTIF("Inactif"),
    LICENSIE("Licensié"),
    NONRECONDUIT("Non reconduit"),
    RETRAITE("Retraité");

    String value;

    private Statut(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }
}
