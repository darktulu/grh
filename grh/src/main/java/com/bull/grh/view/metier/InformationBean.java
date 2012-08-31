package com.bull.grh.view.metier;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bull.grh.service.admin.AdminValueService;
import com.bull.grh.service.exception.CandidatNotFoundException;
import com.bull.grh.service.metier.CandidatService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CompetenceVO;
import com.bull.grh.view.metier.vo.ExperienceVO;
import com.bull.grh.view.metier.vo.FormationVO;
import com.bull.grh.view.utils.vo.ValueListConst;

@Component
@Scope("view")
public class InformationBean implements ValueListConst {

    private static final long serialVersionUID = 1L;

    private String username = SecurityContextHolder.getContext().getAuthentication().getName();
    private CandidatVO candidat;
    
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

    public void addCompetence() {
	competence.setCandidat(candidat);
	competence = candidatService.addorupdatecompetence(competence);
	competences.add(competence);
	competence = new CompetenceVO();
    }

    public void updateCompetence() {
	candidatService.addorupdatecompetence(competence);
	competence = new CompetenceVO();
    }

    public void deleteCompetence() {
	candidatService.deletecompetence(competence);
	competences.remove(competence);
	competence = new CompetenceVO();
    }

    public void addFormation() {
	formation.setCandidat(candidat);
	formation = candidatService.addorupdateFormation(formation);
	formations.add(formation);
	formation = new FormationVO();
    }

    public void updateFormation() {
	candidatService.addorupdateFormation(formation);
	formation = new FormationVO();
    }

    public void deleteFormation() {
	candidatService.deleteFormation(formation);
	formations.remove(formation);
	formation = new FormationVO();
    }

    public void addExperience() {
	experience.setCandidat(candidat);
	experience = candidatService.addorupdateExperience(experience);
	experiences.add(experience);
	experience = new ExperienceVO();
    }

    public void updateExperience() {
	candidatService.addorupdateExperience(experience);
	experience = new ExperienceVO();
    }

    public void deleteExperience() {
	candidatService.deleteExperience(experience);
	experiences.remove(experience);
	experience = new ExperienceVO();
    }

    // Initializers

    public void initFormation() {
	formation = new FormationVO();
    }

    public void initCompetence() {
	competence = new CompetenceVO();
    }

    public void initExperience() {
	experience = new ExperienceVO();
    }

    // GETERS AND SETTERS

    public CandidatVO getCandidat() {
	if (username == null || "".equals(username))
	    username = SecurityContextHolder.getContext().getAuthentication().getName();
	if (candidat == null)
	    try {
		candidat = candidatService.findCandidat(username);
	    } catch (CandidatNotFoundException e) { // TODO message
	    }
	return candidat;
    }

    public void setCandidat(CandidatVO candidat) {
	this.candidat = candidat;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public CompetenceVO getCompetence() {
	return competence;
    }

    public void setCompetence(CompetenceVO competence) {
	this.competence = competence;
    }

    public List<CompetenceVO> getCompetences() {
	if (competences == null || competences.isEmpty())
	    competences = candidatService.loadCompetences(username);
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
	    formations = candidatService.loadFormations(username);
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
	    experiences = candidatService.loadExperiences(username);
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
