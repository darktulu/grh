package com.bull.grh.domaine;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import org.springframework.stereotype.Component;

import com.bull.grh.domaine.types.DynamicField;
import com.bull.grh.domaine.types.EtatEvaluation;

@Entity
@DynamicField(name = "Evaluation", tags = "Formulaire d'évaluation")
@Component
public class Evaluation extends DynamicForm {

    private String pointFortFormation;
    private String pointFaibleFormation;
    private String pointFortExpProf;
    private String pointFaibleExpProf;
    private String pointFortQualitePerso;
    private String pointFaibleQualitePerso;
    private String situationSalActuelleAvantages;
    private String souhaitLocalisation;
    private Date dateDisponibilite;
    private String cabinetRecrutement;
    private String recommandationInterne;
    @Enumerated
    private EtatEvaluation etatEvaluation;

    public String getPointFortFormation() {
	return pointFortFormation;
    }

    public void setPointFortFormation(String pointFortFormation) {
	this.pointFortFormation = pointFortFormation;
    }

    public String getPointFaibleFormation() {
	return pointFaibleFormation;
    }

    public void setPointFaibleFormation(String pointFaibleFormation) {
	this.pointFaibleFormation = pointFaibleFormation;
    }

    public String getPointFortExpProf() {
	return pointFortExpProf;
    }

    public void setPointFortExpProf(String pointFortExpProf) {
	this.pointFortExpProf = pointFortExpProf;
    }

    public String getPointFaibleExpProf() {
	return pointFaibleExpProf;
    }

    public void setPointFaibleExpProf(String pointFaibleExpProf) {
	this.pointFaibleExpProf = pointFaibleExpProf;
    }

    public String getPointFortQualitePerso() {
	return pointFortQualitePerso;
    }

    public void setPointFortQualitePerso(String pointFortQualitePerso) {
	this.pointFortQualitePerso = pointFortQualitePerso;
    }

    public String getPointFaibleQualitePerso() {
	return pointFaibleQualitePerso;
    }

    public void setPointFaibleQualitePerso(String pointFaibleQualitePerso) {
	this.pointFaibleQualitePerso = pointFaibleQualitePerso;
    }

    public String getSituationSalActuelleAvantages() {
	return situationSalActuelleAvantages;
    }

    public void setSituationSalActuelleAvantages(String situationSalActuelleAvantages) {
	this.situationSalActuelleAvantages = situationSalActuelleAvantages;
    }

    public String getSouhaitLocalisation() {
	return souhaitLocalisation;
    }

    public void setSouhaitLocalisation(String souhaitLocalisation) {
	this.souhaitLocalisation = souhaitLocalisation;
    }

    public Date getDateDisponibilite() {
	return dateDisponibilite;
    }

    public void setDateDisponibilite(Date dateDisponibilite) {
	this.dateDisponibilite = dateDisponibilite;
    }

    public String getCabinetRecrutement() {
	return cabinetRecrutement;
    }

    public void setCabinetRecrutement(String cabinetRecrutement) {
	this.cabinetRecrutement = cabinetRecrutement;
    }

    public String getRecommandationInterne() {
	return recommandationInterne;
    }

    public void setRecommandationInterne(String recommandationInterne) {
	this.recommandationInterne = recommandationInterne;
    }

    public EtatEvaluation getEtatEvaluation() {
	return etatEvaluation;
    }

    public void setEtatEvaluation(EtatEvaluation etatEvaluation) {
	this.etatEvaluation = etatEvaluation;
    }

}
