package com.bull.grh.service.metier.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.jbpm.task.Task;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EtatEntretien;
import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmEntretienService;
import com.bull.grh.repos.metier.EntretienDao;
import com.bull.grh.service.metier.EntretienService;
import com.bull.grh.view.metier.vo.EntretienVO;

@Transactional
@Service("entretienService")
public class EntretienServiceImpl implements EntretienService {

    @Inject
    private JbpmEntretienService jbpmEntretienService;
    @Inject
    private EntretienDao entretienDao;
    @Inject
    private DozerBeanMapper mapper;

    @Override
    public void startCETask(Task task) {
	jbpmEntretienService.startTask("ROLE_CE", task); // TODO correct
							 // Contextsecuruty
    }

    @Override
    public void startProcess(EntretienVO entretien) {
	entretien.setEtat(EtatEntretien.ONGOING);
	entretienDao.save(mapper.map(entretien, Entretien.class));
	jbpmEntretienService.startProces(SecurityContextHolder.getContext().getAuthentication().getName(), entretien);
    }

    @Override
    public void startAndCompleteTask(Task task, EntretienVO entretien) {
	jbpmEntretienService.startAndCompleteTask(SecurityContextHolder.getContext().getAuthentication().getName(),
		task, entretien);
    }

    @Override
    public void startRHTask(Task task) {
	jbpmEntretienService.startTask("ROLE_RH", task);
    }

    @Override
    public void completeTaskCE(Task task, EntretienVO entretien) {
	// Persist the appointment with new evaluation's changes
	Entretien newEntretien = mapper.map(entretien, Entretien.class);
	newEntretien = entretienDao.save(newEntretien);
	entretien = mapper.map(newEntretien, EntretienVO.class);

	// continues the process by injecting the appointment
	jbpmEntretienService.completeTask("ROLE_CE", task, entretien); // TODO
								       // correct
								       // Contextsecuruty

    }

    @Override
    public void completeTaskRH(Task task, EntretienVO entretien) {
	// Persist the appointment with new evaluation's changes
	entretien.setEtat(EtatEntretien.COMPLETED);

	Entretien newEntretien = mapper.map(entretien, Entretien.class);
	newEntretien = entretienDao.save(newEntretien);
	entretien = mapper.map(newEntretien, EntretienVO.class);

	// continues the process by injecting the appointment
	jbpmEntretienService.completeTask("ROLE_RH", task, entretien);

    }

    @Override
    @Transactional(readOnly = true)
    public Map<EntretienVO, Task> loadCETaskList() {
	EntretienVO entretienVO = new EntretienVO();
	List<Task> tasks = new ArrayList<Task>();
	Map<EntretienVO, Task> map = new HashMap<EntretienVO, Task>();
	tasks = jbpmEntretienService.getCETaskList(SecurityContextHolder.getContext().getAuthentication().getName());
	for (Task task : tasks) {
	    entretienVO = getEntretienFromTask(task);
	    map.put(entretienVO, task);
	}
	return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<EntretienVO, Task> loadRHTaskList() {
	EntretienVO entretienVO = new EntretienVO();
	List<Task> tasks = new ArrayList<Task>();
	Map<EntretienVO, Task> map = new HashMap<EntretienVO, Task>();
	tasks = jbpmEntretienService.getRHTaskList("ROLE_RH");
	for (Task task : tasks) {
	    entretienVO = getEntretienFromTask(task);
	    map.put(entretienVO, task);
	}
	return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<EntretienVO, Task> loadRHStartedTaskList() {
	EntretienVO entretienVO = new EntretienVO();
	List<Task> tasks = new ArrayList<Task>();
	Map<EntretienVO, Task> map = new HashMap<EntretienVO, Task>();
	tasks = jbpmEntretienService.getRHStartedTaskList("ROLE_RH");
	for (Task task : tasks) {
	    entretienVO = getEntretienFromTask(task);
	    map.put(entretienVO, task);
	}
	return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<EntretienVO, Task> loadCEStartedTaskList() {
	EntretienVO entretienVO = new EntretienVO();
	List<Task> tasks = new ArrayList<Task>();
	Map<EntretienVO, Task> map = new HashMap<EntretienVO, Task>();
	tasks = jbpmEntretienService.getCEStartedTaskList("ROLE_CE"); // TODO
								      // correct
								      // Contextsecuruty
	for (Task task : tasks) {
	    entretienVO = getEntretienFromTask(task);
	    map.put(entretienVO, task);
	}
	return map;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntretienVO> loadEntretiensCE() {
	List<EntretienVO> result = new ArrayList<EntretienVO>();
	for (Entretien entretien : entretienDao.findByPersonneUsernameAndEtat(SecurityContextHolder.getContext()
		.getAuthentication().getName(), EtatEntretien.NEW)) {
	    result.add(mapper.map(entretien, EntretienVO.class));
	}
	return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountCETaskList() {
	return jbpmEntretienService
		.getCountCETaskList(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountCEStartedTaskList() {
	return jbpmEntretienService.getCountCEStartedTaskList("ROLE_CE");
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountRHTaskList() {
	return jbpmEntretienService.getCountRHTaskList("ROLE_RH");
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountRHStartedTaskList() {
	return jbpmEntretienService.getCountRHStartedTaskList("ROLE_RH");
    }

    @Transactional(readOnly = true)
    private EntretienVO getEntretienFromTask(Task task) {
	return (EntretienVO) jbpmEntretienService.getTaskContent(task).get(ProcessConst.APPOINTMENT_ENTRETIEN);
    }

    @Override
    public Long getCountCEEntretiens() {
	return entretienDao.getEntretiensCount(SecurityContextHolder.getContext().getAuthentication().getName(),
		EtatEntretien.NEW);
    }

}
