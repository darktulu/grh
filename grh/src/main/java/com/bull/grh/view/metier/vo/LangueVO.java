package com.bull.grh.view.metier.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.bull.grh.domaine.types.NiveauLangue;

public class LangueVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	@Length(max = 50, min = 2)
	private String value;
	private NiveauLangue niveauLu;
	private NiveauLangue niveauEcrit;
	private NiveauLangue niveauParle;
	private CandidatVO candidat;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public CandidatVO getCandidat() {
		return candidat;
	}

	public void setCandidat(CandidatVO candidat) {
		this.candidat = candidat;
	}

	@Override
	public String toString() {
		return "LangueVO [id=" + id + ", " + (value != null ? "value=" + value + ", " : "")
				+ (niveauLu != null ? "niveauLu=" + niveauLu + ", " : "")
				+ (niveauEcrit != null ? "niveauEcrit=" + niveauEcrit + ", " : "")
				+ (niveauParle != null ? "niveauParle=" + niveauParle + ", " : "")
				+ (candidat != null ? "candidat=" + candidat : "") + "]";
	}

}
