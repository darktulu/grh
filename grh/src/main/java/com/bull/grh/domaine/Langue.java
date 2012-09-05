package com.bull.grh.domaine;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bull.grh.domaine.types.NiveauLangue;

@Entity
public class Langue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated
	private NiveauLangue niveauLu;
	@Enumerated
	private NiveauLangue niveauEcrit;
	@Enumerated
	private NiveauLangue niveauParle;

	private String value;

	@ManyToOne
	private Candidat candidat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NiveauLangue getNiveauLu() {
		return niveauLu;
	}

	public void setNiveauLu(NiveauLangue niveauLu) {
		this.niveauLu = niveauLu;
	}

	public NiveauLangue getNiveauEcrit() {
		return niveauEcrit;
	}

	public void setNiveauEcrit(NiveauLangue niveauEcrit) {
		this.niveauEcrit = niveauEcrit;
	}

	public NiveauLangue getNiveauParle() {
		return niveauParle;
	}

	public void setNiveauParle(NiveauLangue niveauParle) {
		this.niveauParle = niveauParle;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	@Override
	public String toString() {
		return "Langue [" + (id != null ? "id=" + id + ", " : "")
				+ (niveauLu != null ? "niveauLu=" + niveauLu + ", " : "")
				+ (niveauEcrit != null ? "niveauEcrit=" + niveauEcrit + ", " : "")
				+ (niveauParle != null ? "niveauParle=" + niveauParle + ", " : "")
				+ (value != null ? "value=" + value + ", " : "") + (candidat != null ? "candidat=" + candidat : "")
				+ "]";
	}

}
