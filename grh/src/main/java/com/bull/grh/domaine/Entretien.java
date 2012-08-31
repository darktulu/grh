package com.bull.grh.domaine;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bull.grh.domaine.types.DecisionEntretien;
import com.bull.grh.domaine.types.EtatEntretien;

@Entity
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String heure;
    private String contactSurPlace;
    @Enumerated
    private EtatEntretien etat;
    @Enumerated
    private DecisionEntretien decisionEntretien;
    @Column(length = 450)
    private String adresse;

    @ManyToOne
    private Candidature candidature;

    @ManyToOne
    private Personne personne;

    @OneToOne(cascade = CascadeType.ALL)
    public Evaluation evaluation;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public String getHeure() {
	return heure;
    }

    public void setHeure(String heure) {
	this.heure = heure;
    }

    public String getContactSurPlace() {
	return contactSurPlace;
    }

    public void setContactSurPlace(String contactSurPlace) {
	this.contactSurPlace = contactSurPlace;
    }

    public EtatEntretien getEtat() {
	return etat;
    }

    public void setEtat(EtatEntretien etat) {
	this.etat = etat;
    }

    public String getAdresse() {
	return adresse;
    }

    public void setAdresse(String adresse) {
	this.adresse = adresse;
    }

    public Personne getPersonne() {
	return personne;
    }

    public Candidature getCandidature() {
	return candidature;
    }

    public void setCandidature(Candidature candidature) {
	this.candidature = candidature;
    }

    public void setPersonne(Personne personne) {
	this.personne = personne;
    }

    public Evaluation getEvaluation() {
	return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
	this.evaluation = evaluation;
    }

    public DecisionEntretien getDecisionEntretien() {
	return decisionEntretien;
    }

    public void setDecisionEntretien(DecisionEntretien decisionEntretien) {
	this.decisionEntretien = decisionEntretien;
    }

}
