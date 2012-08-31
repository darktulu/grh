package com.bull.grh.domaine;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Conjoint{

	@Id
	@GeneratedValue
	private Long id;
	private String cin;
	private String nom;
	private String nomJeuneFille;
	private String prenom;
	@Temporal(TemporalType.DATE)
	private Date dateNais;
	private String lieuNaissance;
	private boolean activiteProf;
	private String profession;
	private String employeur;
	
	@ManyToOne
	private ValueList nationalite;
	
	@OneToOne
	private DossierCandidature dossierCandidature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ValueList getNationalite() {
		return nationalite;
	}

	public void setNationalite(ValueList nationalite) {
		this.nationalite = nationalite;
	}

	public boolean isActiviteProf() {
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

	public DossierCandidature getDossierCandidature() {
		return dossierCandidature;
	}

	public void setDossierCandidature(DossierCandidature dossierCandidature) {
		this.dossierCandidature = dossierCandidature;
	}

		
}