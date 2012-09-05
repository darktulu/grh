package com.bull.grh.service.metier.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bull.grh.domaine.Authority;
import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.Competence;
import com.bull.grh.domaine.Experience;
import com.bull.grh.domaine.Formation;
import com.bull.grh.domaine.Langue;
import com.bull.grh.process.JbpmRegisterService;
import com.bull.grh.process.exception.InvalidActivationTokenException;
import com.bull.grh.repos.metier.CandidatDao;
import com.bull.grh.repos.metier.CompetenceDao;
import com.bull.grh.repos.metier.ExperienceDao;
import com.bull.grh.repos.metier.FormationDao;
import com.bull.grh.repos.metier.LangueDao;
import com.bull.grh.service.exception.CandidatNotFoundException;
import com.bull.grh.service.exception.CannotRegisterException;
import com.bull.grh.service.metier.CandidatService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CompetenceVO;
import com.bull.grh.view.metier.vo.ExperienceVO;
import com.bull.grh.view.metier.vo.FormationVO;
import com.bull.grh.view.metier.vo.LangueVO;

@Service("candidatService")
@Transactional
public class CandidatServiceImpl implements CandidatService {

	@Inject
	private CandidatDao candidatDao;

	@Inject
	private CompetenceDao competenceDao;

	@Inject
	private FormationDao formationDao;

	@Inject
	private ExperienceDao experienceDao;

	@Inject
	private LangueDao langueDao;

	@Inject
	private JbpmRegisterService jbpmRegisterService;

	@Inject
	private DozerBeanMapper mapper;

	@Override
	public void register(CandidatVO candidatVO) throws CannotRegisterException {

		candidatVO.setPassword(DigestUtils.md5Hex(candidatVO.getPassword()));
		candidatVO.setCodeActivationCompte(DigestUtils.md5Hex("salt :D" + UUID.randomUUID()));

		Candidat candidat = mapper.map(candidatVO, Candidat.class);
		Authority auth = new Authority();
		auth.setRole("ROLE_CANDIDAT");
		List<Authority> authList = new ArrayList<Authority>();
		authList.add(auth);
		candidat.setAuthorities(authList);
		candidat = candidatDao.save(candidat);

		candidatVO = mapper.map(candidat, CandidatVO.class);
		jbpmRegisterService.register(candidatVO);
	}

	@Override
	public void activateAccount(String token) throws InvalidActivationTokenException {
		if (!jbpmRegisterService.activateAccount(token))
			throw new InvalidActivationTokenException();
	}

	@Override
	@Transactional(readOnly = true)
	public CandidatVO findCandidat(String username) throws CandidatNotFoundException {
		Candidat candidat = candidatDao.findByUsername(username);

		if (candidat == null)
			throw new CandidatNotFoundException();

		return mapper.map(candidat, CandidatVO.class);
	}

	@Override
	public CompetenceVO addorupdatecompetence(@Valid CompetenceVO competence) {
		Competence competenceEntity = mapper.map(competence, Competence.class);
		competenceEntity = competenceDao.save(competenceEntity);
		return mapper.map(competenceEntity, CompetenceVO.class);
	}

	@Override
	public void deletecompetence(CompetenceVO competence) {
		Competence competenceEntity = mapper.map(competence, Competence.class);
		competenceDao.delete(competenceEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompetenceVO> loadCompetences(String username) {
		List<CompetenceVO> competences = new ArrayList<CompetenceVO>();
		List<Competence> competencesEntity = competenceDao.findByCandidat_username(username);
		if (competencesEntity == null || competencesEntity.isEmpty()) {
			// TODO nothing here
		} else {
			for (Competence c : competencesEntity)
				competences.add(mapper.map(c, CompetenceVO.class));
		}
		return competences;
	}

	@Override
	public FormationVO addorupdateFormation(@Valid FormationVO formation) {
		Formation formationEntity = mapper.map(formation, Formation.class);
		formationEntity = formationDao.save(formationEntity);
		return mapper.map(formationEntity, FormationVO.class);
	}

	@Override
	public void deleteFormation(FormationVO formation) {
		Formation formationEntity = mapper.map(formation, Formation.class);
		formationDao.delete(formationEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FormationVO> loadFormations(String username) {
		List<FormationVO> formations = new ArrayList<FormationVO>();
		List<Formation> formationsEntity = formationDao.findByCandidat_username(username);
		if (formationsEntity == null || formationsEntity.isEmpty()) {
			// TODO nothing here
		} else {
			for (Formation f : formationsEntity)
				formations.add(mapper.map(f, FormationVO.class));
		}
		return formations;
	}

	@Override
	public ExperienceVO addorupdateExperience(@Valid ExperienceVO experience) {
		Experience experienceEntity = mapper.map(experience, Experience.class);
		experienceEntity = experienceDao.save(experienceEntity);
		return mapper.map(experienceEntity, ExperienceVO.class);
	}

	@Override
	public void deleteExperience(ExperienceVO experience) {
		Experience experienceEntity = mapper.map(experience, Experience.class);
		experienceDao.delete(experienceEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperienceVO> loadExperiences(String username) {
		List<ExperienceVO> experiences = new ArrayList<ExperienceVO>();
		List<Experience> experiencesEntity = experienceDao.findByCandidat_username(username);
		if (experiencesEntity == null || experiencesEntity.isEmpty()) {
			// TODO nothing here
		} else {
			for (Experience e : experiencesEntity)
				experiences.add(mapper.map(e, ExperienceVO.class));
		}
		return experiences;
	}

	@Override
	public LangueVO addorupdateLangue(@Valid LangueVO langue) {
		Langue langueEntity = mapper.map(langue, Langue.class);
		langueEntity = langueDao.save(langueEntity);
		return mapper.map(langueEntity, LangueVO.class);
	}

	@Override
	public void deleteLangue(LangueVO langue) {
		Langue langueEntity = mapper.map(langue, Langue.class);
		langueDao.delete(langueEntity);
	}

	@Override
	public List<LangueVO> loadLangues(String username) {
		List<LangueVO> langues = new ArrayList<LangueVO>();
		List<Langue> languesEntity = langueDao.findByCandidat_username(username);
		if (languesEntity == null || languesEntity.isEmpty()) {
			// TODO nothing here
		} else {
			for (Langue l : languesEntity)
				langues.add(mapper.map(l, LangueVO.class));
		}
		return langues;
	}
}
