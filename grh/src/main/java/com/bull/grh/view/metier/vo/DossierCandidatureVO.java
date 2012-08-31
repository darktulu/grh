package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DossierCandidatureVO implements Serializable {
	
	private Long id;
	private String cin;
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
	private String villeNaissanceVO;
	private String paysNaissanceVO;
	private String provinceNaissance;
	private boolean dossierComplet = false;
	
	private ConjointVO conjoint;
	private List<EnfantVO> enfantVo;
	private List<ExperienceVO> experienceVO;
	private List<ActiviteExtraProfessionnelleVO> activiteExtraProfessionnelleVO;
	private List<QuestionClasseVO> questionClasseVO;
	
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
	public Date getChangementSituationFamiliale() {
		return changementSituationFamiliale;
	}
	public void setChangementSituationFamiliale(Date changementSituationFamiliale) {
		this.changementSituationFamiliale = changementSituationFamiliale;
	}
	public String getFonctionEnvisagee() {
		return fonctionEnvisagee;
	}
	public void setFonctionEnvisagee(String fonctionEnvisagee) {
		this.fonctionEnvisagee = fonctionEnvisagee;
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
	public void setDateCandidaturePecedenteEmploi(
			Date dateCandidaturePecedenteEmploi) {
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
	public String getVilleNaissanceVO() {
		return villeNaissanceVO;
	}
	public void setVilleNaissanceVO(String villeNaissanceVO) {
		this.villeNaissanceVO = villeNaissanceVO;
	}
	public String getPaysNaissanceVO() {
		return paysNaissanceVO;
	}
	public void setPaysNaissanceVO(String paysNaissanceVO) {
		this.paysNaissanceVO = paysNaissanceVO;
	}
	
	public String getProvinceNaissance() {
		return provinceNaissance;
	}
	public void setProvinceNaissance(String provinceNaissance) {
		this.provinceNaissance = provinceNaissance;
	}
	public ConjointVO getConjoint() {
		return conjoint;
	}
	public void setConjoint(ConjointVO conjoint) {
		this.conjoint = conjoint;
	}
	public List<EnfantVO> getEnfantVo() {
		return enfantVo;
	}
	public void setEnfantVo(List<EnfantVO> enfantVo) {
		this.enfantVo = enfantVo;
	}
	public List<ExperienceVO> getExperienceVO() {
		return experienceVO;
	}
	public void setExperienceVO(List<ExperienceVO> experienceVO) {
		this.experienceVO = experienceVO;
	}
	public List<ActiviteExtraProfessionnelleVO> getActiviteExtraProfessionnelleVO() {
		return activiteExtraProfessionnelleVO;
	}
	public void setActiviteExtraProfessionnelleVO(
			List<ActiviteExtraProfessionnelleVO> activiteExtraProfessionnelleVO) {
		this.activiteExtraProfessionnelleVO = activiteExtraProfessionnelleVO;
	}
	public List<QuestionClasseVO> getQuestionClasseVO() {
		return questionClasseVO;
	}
	public void setQuestionClasseVO(List<QuestionClasseVO> questionClasseVO) {
		this.questionClasseVO = questionClasseVO;
	}
	public boolean isDossierComplet() {
		return dossierComplet;
	}
	public void setDossierComplet(boolean dossierComplet) {
		this.dossierComplet = dossierComplet;
	}
	
}
