package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.bull.grh.domaine.types.DecisionEntretien;
import com.bull.grh.domaine.types.EtatEntretien;

public class EntretienVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @NotNull
    private Date date;
    @NotNull
    @Length(max = 20)
    private String heure;
    @NotNull
    @Length(max = 20)
    private String contactSurPlace;
    private EtatEntretien etat;
    private DecisionEntretien decisionEntretien;
    @NotNull
    @Length(max = 250)
    private String adresse;
    private CandidatureVO candidature = new CandidatureVO();
    private EvaluationVO evaluation = new EvaluationVO();
    private boolean start = false;
    private PersonneVO personne = new PersonneVO();
    private int choice;

    public int getChoice() {
	return choice;
    }

    public void setChoice(int choice) {
	this.choice = choice;
    }

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

    public DecisionEntretien getDecisionEntretien() {
	return decisionEntretien;
    }

    public void setDecisionEntretien(DecisionEntretien decisionEntretien) {
	this.decisionEntretien = decisionEntretien;
    }

    public String getAdresse() {
	return adresse;
    }

    public void setAdresse(String adresse) {
	this.adresse = adresse;
    }

    public EvaluationVO getEvaluation() {
	return evaluation;
    }

    public void setEvaluation(EvaluationVO evaluation) {
	this.evaluation = evaluation;
    }

    public boolean isStart() {
	return start;
    }

    public void setStart(boolean start) {
	this.start = start;
    }

    public CandidatureVO getCandidature() {
	return candidature;
    }

    public void setCandidature(CandidatureVO candidature) {
	this.candidature = candidature;
    }

    public PersonneVO getPersonne() {
	return personne;
    }

    public void setPersonne(PersonneVO personne) {
	this.personne = personne;
    }

}
