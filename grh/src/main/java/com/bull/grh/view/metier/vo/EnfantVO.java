package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;

import com.bull.grh.domaine.types.Gender;

public class EnfantVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String prenom;
	private Gender gender;
	private Date dateNais;
	private String nomMere;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateNais() {
		return dateNais;
	}

	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}

	public String getNomMere() {
		return nomMere;
	}

	public void setNomMere(String nomMere) {
		this.nomMere = nomMere;
	}

}
