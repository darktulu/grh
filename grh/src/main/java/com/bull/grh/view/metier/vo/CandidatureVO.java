package com.bull.grh.view.metier.vo;

import java.io.Serializable;

import com.bull.grh.domaine.types.EtatCandidature;

public class CandidatureVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private EtatCandidature etatCandidature;
    private DemandeVO demande = new DemandeVO();
    private CandidatVO candidat = new CandidatVO();
    private DossierCandidatureVO dossierCandidature = new DossierCandidatureVO();

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public EtatCandidature getEtatCandidature() {
	return etatCandidature;
    }

    public void setEtatCandidature(EtatCandidature etatCandidature) {
	this.etatCandidature = etatCandidature;
    }

    public DemandeVO getDemande() {
	return demande;
    }

    public void setDemande(DemandeVO demande) {
	this.demande = demande;
    }

    public CandidatVO getCandidat() {
	return candidat;
    }

    public void setCandidat(CandidatVO candidat) {
	this.candidat = candidat;
    }

    public DossierCandidatureVO getDossierCandidature() {
	return dossierCandidature;
    }

    public void setDossierCandidature(DossierCandidatureVO dossierCandidature) {
	this.dossierCandidature = dossierCandidature;
    }
}
