package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;

public class ConjointVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cin;
	private String nom;
	private String nomJeuneFille;
	private String prenom;
	private Date dateNais;
	private String lieuNaissance;
	private String nationaliteVO;
	private String activProfString;
	private boolean activiteProf;
	private String profession;
	private String employeur;

	public String getActivProfString() {
		return activProfString;
	}

	public void setActivProfString(String activProfString) {
		this.activProfString = activProfString;
		if (activProfString.equals("avec"))
			setActiviteProf(true);
		else
			setActiviteProf(false);
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomJeuneFille() {
		return nomJeuneFille;
	}

	public void setNomJeuneFille(String nomJeuneFille) {
		this.nomJeuneFille = nomJeuneFille;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNais() {
		return dateNais;
	}

	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getNationaliteVO() {
		return nationaliteVO;
	}

	public void setNationaliteVO(String nationaliteVO) {
		this.nationaliteVO = nationaliteVO;
	}

	public boolean isActiviteProf() {
		return activiteProf;
	}

	public boolean getActiviteProf() {
		return activiteProf;
	}

	public void setActiviteProf(boolean activiteProf) {
		this.activiteProf = activiteProf;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEmployeur() {
		return employeur;
	}

	public void setEmployeur(String employeur) {
		this.employeur = employeur;
	}

}
