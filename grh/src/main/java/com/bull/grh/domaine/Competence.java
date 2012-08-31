package com.bull.grh.domaine;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bull.grh.domaine.types.NiveauCompetence;

@Entity
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    private String description;
    @Enumerated
    private NiveauCompetence niveauCompetence;

    @ManyToOne
    private Candidat candidat;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getLibelle() {
	return libelle;
    }

    public void setLibelle(String libelle) {
	this.libelle = libelle;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public NiveauCompetence getNiveauCompetence() {
	return niveauCompetence;
    }

    public void setNiveauCompetence(NiveauCompetence niveauCompetence) {
	this.niveauCompetence = niveauCompetence;
    }

    public Candidat getCandidat() {
	return candidat;
    }

    public void setCandidat(Candidat candidat) {
	this.candidat = candidat;
    }

}