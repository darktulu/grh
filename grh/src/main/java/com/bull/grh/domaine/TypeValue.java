package com.bull.grh.domaine;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TypeValue {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String regex;

	@ManyToOne
	private TypeValue parent;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "typeValue")
	private List<ValueList> values;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public TypeValue getParent() {
		return parent;
	}

	public void setParent(TypeValue parent) {
		this.parent = parent;
	}

	public List<ValueList> getValues() {
		return values;
	}

	public void setValues(List<ValueList> values) {
		this.values = values;
	}

	public TypeValue() {
	}

}