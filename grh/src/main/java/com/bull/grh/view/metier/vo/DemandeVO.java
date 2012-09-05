package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.bull.grh.domaine.types.EtatDemande;
import com.bull.grh.view.admin.vo.ValueVO;

public class DemandeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	@NotNull
	private Date date;
	@NotNull
	@Length(max = 255)
	private String intitulePoste;
	@NotNull
	@Max(value = 50)
	@Min(value = 1)
	private int nbPersonnes;
	private ValueVO disponibilite;
	private Set<ValueVO> villes;
	private Set<ValueVO> pays;
	private ValueVO niveauEtu;
	private ValueVO niveauExp;
	private Set<ValueVO> typeContrats;
	private Set<ValueVO> situationActuelles;
	private EtatDemande etatDemande;
	private DescPosteProfilVO descPosteProfil = new DescPosteProfilVO();
	@Valid
	private PersonneVO personne = new PersonneVO();

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

	public ValueVO getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(ValueVO disponibilite) {
		this.disponibilite = disponibilite;
	}

	public Set<ValueVO> getVilles() {
		return villes;
	}

	public void setVilles(Set<ValueVO> villes) {
		this.villes = villes;
	}

	public Set<ValueVO> getPays() {
		return pays;
	}

	public void setPays(Set<ValueVO> pays) {
		this.pays = pays;
	}

	public ValueVO getNiveauEtu() {
		return niveauEtu;
	}

	public void setNiveauEtu(ValueVO niveauEtu) {
		this.niveauEtu = niveauEtu;
	}

	public ValueVO getNiveauExp() {
		return niveauExp;
	}

	public void setNiveauExp(ValueVO niveauExp) {
		this.niveauExp = niveauExp;
	}

	public Set<ValueVO> getTypeContrats() {
		return typeContrats;
	}

	public void setTypeContrats(Set<ValueVO> typeContrats) {
		this.typeContrats = typeContrats;
	}

	public Set<ValueVO> getSituationActuelles() {
		return situationActuelles;
	}

	public void setSituationActuelles(Set<ValueVO> situationActuelles) {
		this.situationActuelles = situationActuelles;
	}

	public EtatDemande getEtatDemande() {
		return etatDemande;
	}

	public void setEtatDemande(EtatDemande etatDemande) {
		this.etatDemande = etatDemande;
	}

	public DescPosteProfilVO getDescPosteProfil() {
		return descPosteProfil;
	}

	public void setDescPosteProfil(DescPosteProfilVO descPosteProfil) {
		this.descPosteProfil = descPosteProfil;
	}

	public PersonneVO getPersonne() {
		return personne;
	}

	public void setPersonne(PersonneVO personne) {
		this.personne = personne;
	}

	@Override
	public String toString() {
		return "DemandeVO [" + (id != null ? "id=" + id + ", " : "") + (date != null ? "date=" + date + ", " : "")
				+ (intitulePoste != null ? "intitulePoste=" + intitulePoste + ", " : "")
				+ (etatDemande != null ? "etatDemande=" + etatDemande : "") + "]";
	}

}
