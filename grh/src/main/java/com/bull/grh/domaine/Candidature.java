package com.bull.grh.domaine;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bull.grh.domaine.types.EtatCandidature;

@Entity
public class Candidature {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated
    private EtatCandidature etatCandidature;

    @ManyToOne
    private Demande demande;

    @ManyToOne
    private Candidat candidat;

    @OneToOne(cascade=CascadeType.ALL)
    private DossierCandidature dossierCandidature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public DossierCandidature getDossierCandidature() {
        return dossierCandidature;
    }

    public void setDossierCandidature(DossierCandidature dossierCandidature) {
        this.dossierCandidature = dossierCandidature;
    }

    public EtatCandidature getEtatCandidature() {
        return etatCandidature;
    }

    public void setEtatCandidature(EtatCandidature etatCandidature) {
        this.etatCandidature = etatCandidature;
    }



}
