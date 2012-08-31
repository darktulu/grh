package com.bull.grh.domaine.types;

public enum Regexp {

    CHARACTERS_ONLY(
	    "Lettres Seulement",
	    "[a-zA-ZàáâäãåèéêëìíîïòóôöõøùúûüÿýñçčšžÀÁÂÄÃÅÈÉÊËÌÍÎÏÒÓÔÖÕØÙÚÛÜŸÝÑßÇŒÆČŠŽ∂ð]|-|\\s)"),
    NUMERICAL("Chiffres et Nombres", "[^0-9]"),
    ANY("Aucune regexp", ""),
    EMAIL("Email", ".+@.+\\.[a-z]+"),
    PASSWORD("Mot de passe", "^(?=.*\\d)(?=.*[a-zA-Z]).{4,8}$"),
    DATE("Date", ""),
    BOOLEAN("Boolean", "");

    String label;
    String value;

    private Regexp(String label, String value) {
	this.label = label;
	this.value = value;
    }

    public String getLabel() {
	return label;
    }

    public String getValue() {
	return value;
    }

}
