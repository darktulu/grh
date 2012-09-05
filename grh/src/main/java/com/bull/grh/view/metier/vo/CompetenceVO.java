package com.bull.grh.view.metier.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.bull.grh.domaine.types.NiveauCompetence;

public class CompetenceVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	@NotBlank
	@Length(max = 50)
	private String libelle;
	private String description;
	private CandidatVO candidat = new CandidatVO();
	@NotNull
	private NiveauCompetence niveauCompetence;

	private String niveau;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public NiveauCompetence getNiveauCompetence() {
		return niveauCompetence;
	}

	public void setNiveauCompetence(NiveauCompetence niveauCompetence) {
		this.niveauCompetence = niveauCompetence;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CandidatVO getCandidat() {
		return candidat;
	}

	public void setCandidat(CandidatVO candidat) {
		this.candidat = candidat;
	}

	@Override
	public String toString() {
		return "CompetenceVO [" + (id != null ? "id=" + id + ", " : "")
				+ (libelle != null ? "libelle=" + libelle + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (candidat != null ? "candidat=" + candidat + ", " : "")
				+ (niveauCompetence != null ? "niveauCompetence=" + niveauCompetence + ", " : "")
				+ (niveau != null ? "niveau=" + niveau : "") + "]";
	}

}
