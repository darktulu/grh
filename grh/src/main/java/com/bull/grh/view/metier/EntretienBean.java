package com.bull.grh.view.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jbpm.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.service.metier.EntretienService;
import com.bull.grh.view.metier.vo.EntretienVO;
import com.bull.grh.view.metier.vo.EvaluationVO;

@Scope("view")
@Component
public class EntretienBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private EntretienService entretienService;

    private List<EntretienVO> entretiens, entretiensRH, entretiensTask, startedentretiensRH;
    private Map<EntretienVO, Task> entretiensMap;
    private EntretienVO entretien = new EntretienVO();
    private EvaluationVO evaluation = new EvaluationVO();

    public void proceedEntretienCE() {
	entretienService.startProcess(entretien);
	entretiens.remove(entretien);
    }

    public void completeEntretienCE() {
	Task task = entretiensMap.get(entretien);
	entretien.setEvaluation(evaluation);
	entretienService.startAndCompleteTask(task, entretien);
	entretiensMap.remove(entretien);
    }

    public void proceedEntretienRH() {
	Task task = entretiensMap.get(entretien);
	entretienService.startRHTask(task);
	entretiensMap.remove(entretien);
    }

    public void completeEntretienRH() {
	Task task = entretiensMap.get(entretien);
	entretien.setEvaluation(evaluation);
	entretienService.completeTaskRH(task, entretien);
	entretiensMap.remove(entretien);
    }

    public List<EntretienVO> getEntretiensRH() {
	if (entretiensRH == null || entretiensRH.isEmpty()) {
	    entretiensMap = entretienService.loadRHTaskList();
	    entretiensRH = new ArrayList<EntretienVO>(entretiensMap.keySet());
	}
	return entretiensRH;
    }

    public void setEntretiensRH(List<EntretienVO> entretiensRH) {
	this.entretiensRH = entretiensRH;
    }

    public List<EntretienVO> getStartedentretiensRH() {
	if (startedentretiensRH == null || startedentretiensRH.isEmpty()) {
	    entretiensMap = entretienService.loadRHStartedTaskList();
	    startedentretiensRH = new ArrayList<EntretienVO>(entretiensMap.keySet());
	}
	return startedentretiensRH;
    }

    public void setStartedentretiensRH(List<EntretienVO> startedentretiensRH) {
	this.startedentretiensRH = startedentretiensRH;
    }

    public EntretienService getEntretienService() {
	return entretienService;
    }

    public void setEntretienService(EntretienService entretienService) {
	this.entretienService = entretienService;
    }

    public List<EntretienVO> getEntretiens() {
	if (entretiens == null || entretiens.isEmpty()) {
	    entretiens = entretienService.loadEntretiensCE();
	}
	return entretiens;
    }

    public void setEntretiens(List<EntretienVO> entretiens) {
	this.entretiens = entretiens;
    }

    public List<EntretienVO> getEntretiensTask() {
	if (entretiensTask == null || entretiensTask.isEmpty()) {
	    entretiensMap = entretienService.loadCETaskList();
	    entretiensTask = new ArrayList<EntretienVO>(entretiensMap.keySet());
	}
	return entretiensTask;
    }

    public void setEntretiensTask(List<EntretienVO> startedentretiens) {
	this.entretiensTask = startedentretiens;
    }

    public Map<EntretienVO, Task> getEntretiensMap() {
	return entretiensMap;
    }

    public void setEntretiensMap(Map<EntretienVO, Task> entretiensMap) {
	this.entretiensMap = entretiensMap;
    }

    public EntretienVO getEntretien() {
	return entretien;
    }

    public void setEntretien(EntretienVO entretien) {
	this.entretien = entretien;
    }

    public EvaluationVO getEvaluation() {
	return evaluation;
    }

    public void setEvaluation(EvaluationVO evaluation) {
	this.evaluation = evaluation;
    }
}
