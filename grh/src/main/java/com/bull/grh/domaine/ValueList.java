package com.bull.grh.domaine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ValueList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String value;

	@ManyToOne
	private TypeValue typeValue;

	@ManyToOne
	private ValueList parent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public TypeValue getTypeValue() {
	    return typeValue;
	}

	public void setTypeValue(TypeValue typeValue) {
	    this.typeValue = typeValue;
	}

	public ValueList getParent() {
		return parent;
	}

	public void setParent(ValueList valueList) {
		this.parent = valueList;
	}

	public ValueList(String value, TypeValue typeValue, ValueList valueList) {
		super();
		this.value = value;
		this.typeValue = typeValue;
		this.parent = valueList;
	}

	public ValueList() {
	}

}