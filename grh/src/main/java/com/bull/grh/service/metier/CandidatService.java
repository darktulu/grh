package com.bull.grh.service.metier;

import java.util.List;

import javax.validation.Valid;

import com.bull.grh.process.exception.InvalidActivationTokenException;
import com.bull.grh.service.exception.CandidatNotFoundException;
import com.bull.grh.service.exception.CannotRegisterException;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CompetenceVO;
import com.bull.grh.view.metier.vo.ExperienceVO;
import com.bull.grh.view.metier.vo.FormationVO;

public interface CandidatService {

    // TODO comment
    public void register(CandidatVO candidatVO) throws CannotRegisterException;

    // TODO comment
    public void activateAccount(String token) throws InvalidActivationTokenException;

    // TODO comment
    public CandidatVO findCandidat(String username) throws CandidatNotFoundException;

    // Competence CRUD
    public CompetenceVO addorupdatecompetence(@Valid CompetenceVO competence);

    public void deletecompetence(CompetenceVO competence);

    public List<CompetenceVO> loadCompetences(String username);

    // Formation CRUD
    public FormationVO addorupdateFormation(@Valid FormationVO formation);

    public void deleteFormation(FormationVO formation);

    public List<FormationVO> loadFormations(String username);

    // Experience CRUD
    public void deleteExperience(ExperienceVO experience);

    public List<ExperienceVO> loadExperiences(String username);

    public ExperienceVO addorupdateExperience(@Valid ExperienceVO experience);
}
