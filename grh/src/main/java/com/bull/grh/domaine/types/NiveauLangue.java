package com.bull.grh.domaine.types;

import java.io.Serializable;

public enum NiveauLangue implements Serializable{

	FAIBLE("Faible"), MOYEN("Moyen"), BON("Bon");

	String value;

	private NiveauLangue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
