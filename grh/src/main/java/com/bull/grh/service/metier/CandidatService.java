package com.bull.grh.service.metier;

import java.util.List;

import javax.validation.Valid;

import com.bull.grh.service.exception.InvalidActivationTokenException;
import com.bull.grh.service.exception.CandidatNotFoundException;
import com.bull.grh.service.exception.CannotRegisterException;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CompetenceVO;
import com.bull.grh.view.metier.vo.ExperienceVO;
import com.bull.grh.view.metier.vo.FormationVO;
import com.bull.grh.view.metier.vo.LangueVO;

public interface CandidatService {
    // TODO comment
    void register(CandidatVO candidatVO) throws CannotRegisterException;

    // TODO comment
    void activateAccount(String token) throws InvalidActivationTokenException;

    // TODO comment
    CandidatVO findCandidat(String username) throws CandidatNotFoundException;

    // Competence CRUD
    CompetenceVO addorupdatecompetence(@Valid CompetenceVO competence);

    void deletecompetence(CompetenceVO competence);

    List<CompetenceVO> loadCompetences(String username);

    // Formation CRUD
    FormationVO addorupdateFormation(@Valid FormationVO formation);

    void deleteFormation(FormationVO formation);

    List<FormationVO> loadFormations(String username);

    // Experience CRUD
    void deleteExperience(ExperienceVO experience);

    List<ExperienceVO> loadExperiences(String username);

    ExperienceVO addorupdateExperience(@Valid ExperienceVO experience);

	LangueVO addorupdateLangue(@Valid LangueVO langue);

	void deleteLangue(LangueVO langue);

	List<LangueVO> loadLangues(String username);
}
