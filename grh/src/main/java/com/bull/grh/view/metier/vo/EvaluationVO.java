package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import com.bull.grh.domaine.types.EtatEvaluation;


public class EvaluationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@Size(max=100)
	private String pointFortFormation;
	@Size(max=100)
	private String pointFaibleFormation;
	@Size(max=100)
	private String pointFortExpProf;
	@Size(max=100)
	private String pointFaibleExpProf;
	@Size(max=100)
	private String pointFortQualitePerso;
	@Size(max=100)
	private String pointFaibleQualitePerso;
	@Size(max=300)
	private String situationSalActuelleAvantages;
	@Size(max=100)
	private String souhaitLocalisation;
	private Date dateDisponibilite;
	@Size(max=100)
	private String cabinetRecrutement;
	@Size(max=100)
	private String recommandationInterne;
	private EtatEvaluation etatEvaluation;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public void setSituationSalActuelleAvantages(
			String situationSalActuelleAvantages) {
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
