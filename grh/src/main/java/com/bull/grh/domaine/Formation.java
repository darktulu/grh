package com.bull.grh.domaine;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Formation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String etablissement;
    private String titreDiplome;
    private String specialite;
    private String optionFormation;
    @Temporal(TemporalType.DATE)
    private Date entrydate;
    @Temporal(TemporalType.DATE)
    private Date leavingdate;
    
    @ManyToOne
    private Candidat candidat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getTitreDiplome() {
        return titreDiplome;
    }

    public void setTitreDiplome(String titreDiplome) {
        this.titreDiplome = titreDiplome;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getOptionFormation() {
        return optionFormation;
    }

    public void setOptionFormation(String optionFormation) {
        this.optionFormation = optionFormation;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public Date getLeavingdate() {
        return leavingdate;
    }

    public void setLeavingdate(Date leavingdate) {
        this.leavingdate = leavingdate;
    }

}
