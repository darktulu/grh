package com.bull.grh.view.metier.vo;

import java.io.Serializable;

import com.bull.grh.domaine.types.Gender;

public class DescPosteProfilVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String missionPoste;
    private String rattachementStructurel;
    private int nbPerSupervise;
    private String activitesPples;
    private String conditionTravail;
    private String sensibilitePoste;
    private String moyensTechniques;
    private Gender gender;
    private int ageProfil;
    private String formationReference;
    private String niveauEtude;
    private boolean expIndispensable;
    private boolean expPosteSimilaire;
    private double dureeExp;
    private String connaissanceTheorique;
    private String connaissanceTechnique;
    private String competenceOrgRel;
    private String autre;
    private String responsableHierarchique;
    private String directeurEntite;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getMissionPoste() {
	return missionPoste;
    }

    public void setMissionPoste(String missionPoste) {
	this.missionPoste = missionPoste;
    }

    public String getRattachementStructurel() {
	return rattachementStructurel;
    }

    public void setRattachementStructurel(String rattachementStructurel) {
	this.rattachementStructurel = rattachementStructurel;
    }

    public int getNbPerSupervise() {
	return nbPerSupervise;
    }

    public void setNbPerSupervise(int nbPerSupervise) {
	this.nbPerSupervise = nbPerSupervise;
    }

    public String getActivitesPples() {
	return activitesPples;
    }

    public void setActivitesPples(String activitesPples) {
	this.activitesPples = activitesPples;
    }

    public String getConditionTravail() {
	return conditionTravail;
    }

    public void setConditionTravail(String conditionTravail) {
	this.conditionTravail = conditionTravail;
    }

    public String getSensibilitePoste() {
	return sensibilitePoste;
    }

    public void setSensibilitePoste(String sensibilitePoste) {
	this.sensibilitePoste = sensibilitePoste;
    }

    public String getMoyensTechniques() {
	return moyensTechniques;
    }

    public void setMoyensTechniques(String moyensTechniques) {
	this.moyensTechniques = moyensTechniques;
    }

    public Gender getGender() {
	return gender;
    }

    public void setGender(Gender gender) {
	this.gender = gender;
    }

    public int getAgeProfil() {
	return ageProfil;
    }

    public void setAgeProfil(int ageProfil) {
	this.ageProfil = ageProfil;
    }

    public String getFormationReference() {
	return formationReference;
    }

    public void setFormationReference(String formationReference) {
	this.formationReference = formationReference;
    }

    public String getNiveauEtude() {
	return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
	this.niveauEtude = niveauEtude;
    }

    public boolean isExpIndispensable() {
	return expIndispensable;
    }

    public void setExpIndispensable(boolean expIndispensable) {
	this.expIndispensable = expIndispensable;
    }

    public boolean isExpPosteSimilaire() {
	return expPosteSimilaire;
    }

    public void setExpPosteSimilaire(boolean expPosteSimilaire) {
	this.expPosteSimilaire = expPosteSimilaire;
    }

    public double getDureeExp() {
	return dureeExp;
    }

    public void setDureeExp(double dureeExp) {
	this.dureeExp = dureeExp;
    }

    public String getConnaissanceTheorique() {
	return connaissanceTheorique;
    }

    public void setConnaissanceTheorique(String connaissanceTheorique) {
	this.connaissanceTheorique = connaissanceTheorique;
    }

    public String getConnaissanceTechnique() {
	return connaissanceTechnique;
    }

    public void setConnaissanceTechnique(String connaissanceTechnique) {
	this.connaissanceTechnique = connaissanceTechnique;
    }

    public String getCompetenceOrgRel() {
	return competenceOrgRel;
    }

    public void setCompetenceOrgRel(String competenceOrgRel) {
	this.competenceOrgRel = competenceOrgRel;
    }

    public String getAutre() {
	return autre;
    }

    public void setAutre(String autre) {
	this.autre = autre;
    }

    public String getResponsableHierarchique() {
	return responsableHierarchique;
    }

    public void setResponsableHierarchique(String responsableHierarchique) {
	this.responsableHierarchique = responsableHierarchique;
    }

    public String getDirecteurEntite() {
	return directeurEntite;
    }

    public void setDirecteurEntite(String directeurEntite) {
	this.directeurEntite = directeurEntite;
    }

}
