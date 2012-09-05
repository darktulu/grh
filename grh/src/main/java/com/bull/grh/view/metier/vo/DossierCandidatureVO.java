package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DossierCandidatureVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cin;
	private String villeNaissance;
	private String paysNaissance;
	private String provinceNaissance;
	private Date dateNaissance;
	
	private String fonctionEnvisagee;
	private Date changementSituationFamiliale;
	private String relationPersoProfessionnelle;
	private String posteOccupeImportant;
	private int nbPersonnesEncadrees;
	private double montantBudgetGere;
	private Date dateCandidaturePrecedentStage;
	private Date dateCandidaturePecedenteEmploi;
	private String attentes;
	private double salNetMensuelSouhaite;
	private String emploiSouhaite;
	private Date datePriseFonction;
	private boolean dossierComplet = false;

	private ConjointVO conjoint;
	private List<EnfantVO> enfant;
	private List<ExperienceVO> experience;
	private List<ActiviteExtraProfessionnelleVO> activiteExtraProfessionnelle;
	private List<QuestionClasseVO> questionClasse;

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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getFonctionEnvisagee() {
		return fonctionEnvisagee;
	}

	public void setFonctionEnvisagee(String fonctionEnvisagee) {
		this.fonctionEnvisagee = fonctionEnvisagee;
	}

	public Date getChangementSituationFamiliale() {
		return changementSituationFamiliale;
	}

	public void setChangementSituationFamiliale(Date changementSituationFamiliale) {
		this.changementSituationFamiliale = changementSituationFamiliale;
	}

	public String getRelationPersoProfessionnelle() {
		return relationPersoProfessionnelle;
	}

	public void setRelationPersoProfessionnelle(String relationPersoProfessionnelle) {
		this.relationPersoProfessionnelle = relationPersoProfessionnelle;
	}

	public String getPosteOccupeImportant() {
		return posteOccupeImportant;
	}

	public void setPosteOccupeImportant(String posteOccupeImportant) {
		this.posteOccupeImportant = posteOccupeImportant;
	}

	public int getNbPersonnesEncadrees() {
		return nbPersonnesEncadrees;
	}

	public void setNbPersonnesEncadrees(int nbPersonnesEncadrees) {
		this.nbPersonnesEncadrees = nbPersonnesEncadrees;
	}

	public double getMontantBudgetGere() {
		return montantBudgetGere;
	}

	public void setMontantBudgetGere(double montantBudgetGere) {
		this.montantBudgetGere = montantBudgetGere;
	}

	public Date getDateCandidaturePrecedentStage() {
		return dateCandidaturePrecedentStage;
	}

	public void setDateCandidaturePrecedentStage(Date dateCandidaturePrecedentStage) {
		this.dateCandidaturePrecedentStage = dateCandidaturePrecedentStage;
	}

	public Date getDateCandidaturePecedenteEmploi() {
		return dateCandidaturePecedenteEmploi;
	}

	public void setDateCandidaturePecedenteEmploi(Date dateCandidaturePecedenteEmploi) {
		this.dateCandidaturePecedenteEmploi = dateCandidaturePecedenteEmploi;
	}

	public String getAttentes() {
		return attentes;
	}

	public void setAttentes(String attentes) {
		this.attentes = attentes;
	}

	public double getSalNetMensuelSouhaite() {
		return salNetMensuelSouhaite;
	}

	public void setSalNetMensuelSouhaite(double salNetMensuelSouhaite) {
		this.salNetMensuelSouhaite = salNetMensuelSouhaite;
	}

	public String getEmploiSouhaite() {
		return emploiSouhaite;
	}

	public void setEmploiSouhaite(String emploiSouhaite) {
		this.emploiSouhaite = emploiSouhaite;
	}

	public Date getDatePriseFonction() {
		return datePriseFonction;
	}

	public void setDatePriseFonction(Date datePriseFonction) {
		this.datePriseFonction = datePriseFonction;
	}

	public String getVilleNaissance() {
		return villeNaissance;
	}

	public void setVilleNaissance(String villeNaissance) {
		this.villeNaissance = villeNaissance;
	}

	public String getPaysNaissance() {
		return paysNaissance;
	}

	public void setPaysNaissance(String paysNaissance) {
		this.paysNaissance = paysNaissance;
	}

	public String getProvinceNaissance() {
		return provinceNaissance;
	}

	public void setProvinceNaissance(String provinceNaissance) {
		this.provinceNaissance = provinceNaissance;
	}

	public boolean isDossierComplet() {
		return dossierComplet;
	}

	public void setDossierComplet(boolean dossierComplet) {
		this.dossierComplet = dossierComplet;
	}

	public ConjointVO getConjoint() {
		return conjoint;
	}

	public void setConjoint(ConjointVO conjoint) {
		this.conjoint = conjoint;
	}

	public List<EnfantVO> getEnfant() {
		return enfant;
	}

	public void setEnfant(List<EnfantVO> enfant) {
		this.enfant = enfant;
	}

	public List<ExperienceVO> getExperience() {
		return experience;
	}

	public void setExperience(List<ExperienceVO> experience) {
		this.experience = experience;
	}

	public List<ActiviteExtraProfessionnelleVO> getActiviteExtraProfessionnelle() {
		return activiteExtraProfessionnelle;
	}

	public void setActiviteExtraProfessionnelle(List<ActiviteExtraProfessionnelleVO> activiteExtraProfessionnelle) {
		this.activiteExtraProfessionnelle = activiteExtraProfessionnelle;
	}

	public List<QuestionClasseVO> getQuestionClasse() {
		return questionClasse;
	}

	public void setQuestionClasse(List<QuestionClasseVO> questionClasse) {
		this.questionClasse = questionClasse;
	}

}
