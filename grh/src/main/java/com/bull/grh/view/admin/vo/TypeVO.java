package com.bull.grh.view.admin.vo;

import java.io.Serializable;

public class TypeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nom;
	private TypeVO parent = new TypeVO();
	private String regex;

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

	public TypeVO getParent() {
		return parent;
	}

	public void setParent(TypeVO parent) {
		this.parent = parent;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public TypeVO(String nom, TypeVO parent) {
		super();
		this.nom = nom;
		this.parent = parent;
	}

	public TypeVO(String nom) {
		super();
		this.nom = nom;
	}

	public TypeVO() {
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
