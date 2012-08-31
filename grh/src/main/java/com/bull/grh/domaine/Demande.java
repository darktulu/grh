package com.bull.grh.domaine;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.bull.grh.domaine.types.EtatDemande;

@Entity
public class Demande {

	@Id
	@GeneratedValue
	private Long id;
	private Date date;
	private String intitulePoste;
	private int nbPersonnes;
	@ManyToOne
	private ValueList disponibilite;
	@ManyToOne
	private ValueList niveauEtu;
	@ManyToOne
	private ValueList niveauExp;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ValueList> villes;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ValueList> pays;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ValueList> typeContrats;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ValueList> situationActuelles;
	@Enumerated
	private EtatDemande etatDemande;

	@ManyToOne
	private Personne personne;

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

	public EtatDemande getEtatDemande() {
		return etatDemande;
	}

	public void setEtatDemande(EtatDemande etatDemande) {
		this.etatDemande = etatDemande;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public String getIntitulePoste() {
		return intitulePoste;
	}

	public void setIntitulePoste(String intitulePoste) {
		this.intitulePoste = intitulePoste;
	}

	public int getNbPersonnes() {
		return nbPersonnes;
	}

	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	public ValueList getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(ValueList disponibilite) {
		this.disponibilite = disponibilite;
	}

	public ValueList getNiveauEtu() {
		return niveauEtu;
	}

	public void setNiveauEtu(ValueList niveauEtu) {
		this.niveauEtu = niveauEtu;
	}

	public ValueList getNiveauExp() {
		return niveauExp;
	}

	public void setNiveauExp(ValueList niveauExp) {
		this.niveauExp = niveauExp;
	}

	public Set<ValueList> getVilles() {
		return villes;
	}

	public void setVilles(Set<ValueList> villes) {
		this.villes = villes;
	}

	public Set<ValueList> getPays() {
		return pays;
	}

	public void setPays(Set<ValueList> pays) {
		this.pays = pays;
	}

	public Set<ValueList> getTypeContrats() {
		return typeContrats;
	}

	public void setTypeContrats(Set<ValueList> typeContrats) {
		this.typeContrats = typeContrats;
	}

	public Set<ValueList> getSituationActuelles() {
		return situationActuelles;
	}

	public void setSituationActuelles(Set<ValueList> situationActuelles) {
		this.situationActuelles = situationActuelles;
	}

}