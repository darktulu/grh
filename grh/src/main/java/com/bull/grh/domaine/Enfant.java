package com.bull.grh.domaine;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bull.grh.domaine.types.Gender;


@Entity
public class Enfant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String prenom;
	@Enumerated
	private Gender sexe;
	@Temporal(TemporalType.DATE)
	private Date dateNais;
	private String nomMere;

	@ManyToOne
	private DossierCandidature dossierCandidature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Gender getSexe() {
		return sexe;
	}

	public void setSexe(Gender sexe) {
		this.sexe = sexe;
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

	public DossierCandidature getDossierCandidature() {
		return dossierCandidature;
	}

	public void setDossierCandidature(DossierCandidature dossierCandidature) {
		this.dossierCandidature = dossierCandidature;
	}



}