package com.bull.grh.domaine.types;

public enum JavaType {

	STRING("String","STRING"),
	INT("int","INT"),
	LONG("long","LONG"),
	CHAR("char","CHAR"),
	DOUBLE("double","DOUBLE"),
	DATE("Date","DATE"),
	BOOLEAN("boolean","BOOLEAN");
	
	String label;
	String value;
	
	private JavaType(String label, String value) {
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
