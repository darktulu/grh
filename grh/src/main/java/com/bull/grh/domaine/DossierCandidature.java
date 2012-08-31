package com.bull.grh.domaine;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.bull.grh.domaine.types.DynamicField;

@Entity
@DynamicField(name="Dossier de Candidature",tags="Formulaire de Dossier du Candidat")
@Component
public class DossierCandidature extends DynamicForm {

	private String cin;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	private String fonctionEnvisagee;
	@Temporal(TemporalType.DATE)
	private Date changementSituationFamiliale;
	private String relationPersoProfessionnelle;
	private String posteOccupeImportant;
	private int nbPersonnesEncadrees;
	private double montantBudgetGere;
	@Temporal(TemporalType.DATE)
	private Date dateCandidaturePrecedentStage;
	@Temporal(TemporalType.DATE)
	private Date dateCandidaturePecedenteEmploi;
	private String attentes;
	private double salNetMensuelSouhaite;
	private String emploiSouhaite;
	@Temporal(TemporalType.DATE)
	private Date datePriseFonction;
	private String provinceNaissance;
	private boolean dossierComplet;
	
	@ManyToOne
	private ValueList villeNaissance;
	@ManyToOne
	private ValueList paysNaissance;

		
	@OneToOne(cascade=CascadeType.ALL)
	private Conjoint conjoint;
	
	@OneToOne(mappedBy="dossierCandidature")
	private Candidature candidature;
	
	
	@OneToMany(mappedBy="dossierCandidature", cascade=CascadeType.ALL)
	private List<Enfant> enfants;
	
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

	public ValueList getVilleNaissance() {
		return villeNaissance;
	}

	public void setVilleNaissance(ValueList villeNaissance) {
		this.villeNaissance = villeNaissance;
	}

	public ValueList getPaysNaissance() {
		return paysNaissance;
	}

	public void setPaysNaissance(ValueList paysNaissance) {
		this.paysNaissance = paysNaissance;
	}

	

	public String getProvinceNaissance() {
		return provinceNaissance;
	}

	public void setProvinceNaissance(String provinceNaissance) {
		this.provinceNaissance = provinceNaissance;
	}

	public Conjoint getConjoint() {
		return conjoint;
	}

	public void setConjoint(Conjoint conjoint) {
		this.conjoint = conjoint;
	}

	public boolean isDossierComplet() {
		return dossierComplet;
	}

	public void setDossierComplet(boolean dossierComplet) {
		this.dossierComplet = dossierComplet;
	}

	public List<Enfant> getEnfants() {
		return enfants;
	}

	public void setEnfants(List<Enfant> enfants) {
		this.enfants = enfants;
	}

	public Candidature getCandidature() {
		return candidature;
	}

	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}
	
}
