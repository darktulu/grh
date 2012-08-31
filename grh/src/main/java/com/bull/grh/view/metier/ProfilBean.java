package com.bull.grh.view.metier;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.service.admin.AdminValueService;
import com.bull.grh.service.metier.CandidatService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CompetenceVO;
import com.bull.grh.view.metier.vo.ExperienceVO;
import com.bull.grh.view.metier.vo.FormationVO;
import com.bull.grh.view.utils.vo.ValueListConst;

@Component
@Scope("request")
public class ProfilBean implements ValueListConst {

    private static final long serialVersionUID = 1L;

    private String username;
    private CandidatVO candidat = new CandidatVO();

    private CompetenceVO competence = new CompetenceVO();
    private List<CompetenceVO> competences;

    private FormationVO formation = new FormationVO();
    private List<FormationVO> formations;

    private ExperienceVO experience = new ExperienceVO();
    private List<ExperienceVO> experiences;

    @Inject
    transient private CandidatService candidatService;
    @Inject
    transient private AdminValueService adminValueService;

    @PostConstruct
    public void init() {
	username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public CandidatVO getCandidat() {
	return candidat;
    }

    public void setCandidat(CandidatVO candidat) {
	this.candidat = candidat;
    }

    public CompetenceVO getCompetence() {
	return competence;
    }

    public void setCompetence(CompetenceVO competence) {
	this.competence = competence;
    }

    public List<CompetenceVO> getCompetences() {
	if (competences == null || competences.isEmpty())
	    competences = candidatService.loadCompetences(candidat.getUsername());
	return competences;
    }

    public void setCompetences(List<CompetenceVO> competences) {
	this.competences = competences;
    }

    public CandidatService getCandidatService() {
	return candidatService;
    }

    public void setCandidatService(CandidatService candidatService) {
	this.candidatService = candidatService;
    }

    public AdminValueService getAdminValueService() {
	return adminValueService;
    }

    public FormationVO getFormation() {
	return formation;
    }

    public void setFormation(FormationVO formation) {
	this.formation = formation;
    }

    public List<FormationVO> getFormations() {
	if (formations == null || formations.isEmpty())
	    formations = candidatService.loadFormations(candidat.getUsername());
	return formations;
    }

    public ExperienceVO getExperience() {
	return experience;
    }

    public void setExperience(ExperienceVO experience) {
	this.experience = experience;
    }

    public List<ExperienceVO> getExperiences() {
	if (experiences == null || experiences.isEmpty())
	    experiences = candidatService.loadExperiences(candidat.getUsername());
	return experiences;
    }

    public void setExperiences(List<ExperienceVO> experiences) {
	this.experiences = experiences;
    }

    public void setFormations(List<FormationVO> formations) {
	this.formations = formations;
    }

    public void setAdminValueService(AdminValueService adminValueService) {
	this.adminValueService = adminValueService;
    }

}
