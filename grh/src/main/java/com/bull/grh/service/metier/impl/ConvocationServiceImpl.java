package com.bull.grh.service.metier.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.jbpm.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EtatCandidature;
import com.bull.grh.domaine.types.EtatEntretien;
import com.bull.grh.domaine.types.EtatEvaluation;
import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmConvocationService;
import com.bull.grh.repos.metier.CandidatureDao;
import com.bull.grh.repos.metier.EntretienDao;
import com.bull.grh.service.metier.ConvocationService;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;

@Transactional
@Service("convocationService")
public class ConvocationServiceImpl implements ConvocationService {

	@Inject
	private JbpmConvocationService jbpmConvocationService;
	@Inject
	private CandidatureDao candidatureDao;
	@Inject
	private DozerBeanMapper mapper;
	@Inject
	private EntretienDao entretienDao;

	@Override
	public void cancelConvocation(Task task, CandidatureVO candidature) {
		// change the state of the appointment
		candidature.setEtatCandidature(EtatCandidature.COMPLETED);

		// update the state of the Entities
		candidatureDao.save(mapper.map(candidature, Candidature.class));

		// complete task without any parameters (it will end the process for
		// this candidate)
		jbpmConvocationService.startAndCompleteTask("ROLE_RH", task, null,
				false);
	}

	@Override
	public void startConvocation(Task task) {
		jbpmConvocationService.startTask("ROLE_RH", task);
	}

	@Override
	public void completeConvocation(Task task, CandidatureVO candidature,
			EntretienVO entretien) {
		// Persist the appointment with new changes and creates evaluation for
		// this appointment
		entretien.setCandidature(candidature);
		entretien.setEtat(EtatEntretien.NEW);
		entretien.getEvaluation().setEtatEvaluation(EtatEvaluation.NEW);

		Entretien newEntretien = mapper.map(entretien, Entretien.class);
		newEntretien = entretienDao.save(newEntretien);
		entretien = mapper.map(newEntretien, EntretienVO.class);

		// continues the process by injecting the appointment
		jbpmConvocationService.completeTask("ROLE_RH", task, entretien, true);

	}

	@Override
	@Transactional(readOnly = true)
	public Map<CandidatureVO, Task> loadTaskList() {
		CandidatureVO candidatureVO = new CandidatureVO();
		List<Task> tasks = new ArrayList<Task>();
		Map<CandidatureVO, Task> map = new HashMap<CandidatureVO, Task>();
		tasks = jbpmConvocationService.getTaskList("ROLE_RH");
		for (Task task : tasks) {
			candidatureVO = getCandidatureFromTask(task);
			map.put(candidatureVO, task);
		}
		return map;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<CandidatureVO, Task> loadStartedTaskList() {
		CandidatureVO candidatureVO = new CandidatureVO();
		List<Task> tasks = new ArrayList<Task>();
		Map<CandidatureVO, Task> map = new HashMap<CandidatureVO, Task>();
		tasks = jbpmConvocationService.getStartedTaskList("ROLE_RH");
		for (Task task : tasks) {
			candidatureVO = getCandidatureFromTask(task);
			map.put(candidatureVO, task);
		}
		return map;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getCountTaskList() {
		return jbpmConvocationService.getCountTaskList("ROLE_RH");
	}

	@Override
	@Transactional(readOnly = true)
	public Long getCountStartedTaskList() {
		return jbpmConvocationService.getCountStartedTaskList("ROLE_RH");
	}

	@Transactional(readOnly = true)
	private CandidatureVO getCandidatureFromTask(Task task) {
		return (CandidatureVO) jbpmConvocationService.getTaskContent(task).get(
				ProcessConst.CONVOCATION_CANDIDATURE);
	}

}
