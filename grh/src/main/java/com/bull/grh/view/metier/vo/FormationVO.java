package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class FormationVO implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank
    private String etablissement;
    @NotBlank
    private String titreDiplome;
    @NotBlank
    private String specialite;
    @NotBlank
    private String optionFormation;
    @NotNull
    private Date entrydate;
    @NotNull
    private Date leavingdate;
    
    private CandidatVO candidat = new CandidatVO();
    
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
    public CandidatVO getCandidat() {
        return candidat;
    }
    public void setCandidat(CandidatVO candidat) {
        this.candidat = candidat;
    }
    
}
