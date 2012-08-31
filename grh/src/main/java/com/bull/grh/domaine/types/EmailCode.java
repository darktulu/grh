package com.bull.grh.domaine.types;

public enum EmailCode {
	
	REGISTER_NEW_ACCOUNT("Account acctivation"),
	REGISTER_COMPLETED("Account activated"),
	REGISTER_RECALL("Account activation recall"),
	CALL_APPOINTMENT("Calling for appointment"), 
	CALL_WORK("Calling for work"),
	CALL_INTERNSHIP("Calling for internship"),
	REJECTED_AFTER_APP("Rejected after appointment"),
	CANDIDATURE("Traitement de candidature"),
	PASSWORD_FORGOT("Password forgoten");

	String value;
	
	private EmailCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
