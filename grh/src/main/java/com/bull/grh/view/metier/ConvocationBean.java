package com.bull.grh.view.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jbpm.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.service.metier.ConvocationService;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;

@Scope("view")
@Component
public class ConvocationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private ConvocationService convocationService;

    private List<CandidatureVO> convocations, startedconvocations;
    private Map<CandidatureVO, Task> convocationsMap;
    private CandidatureVO convocation = new CandidatureVO();
    private EntretienVO entretien = new EntretienVO();

    public void proceedConvocation() {
	Task task = convocationsMap.get(convocation);
	convocationService.startConvocation(task);
	convocationsMap.remove(convocation);
    }

    public void cancelConvocation() {
	Task task = convocationsMap.get(convocation);
	convocationService.cancelConvocation(task, convocation);
	convocationsMap.remove(convocation);
    }

    public void completeConvocation() {
	Task task = convocationsMap.get(convocation);
	convocationService.completeConvocation(task, convocation, entretien);
	convocationsMap.remove(convocation);
    }

    public ConvocationService getConvocationService() {
	return convocationService;
    }

    public void setConvocationService(ConvocationService convocationService) {
	this.convocationService = convocationService;
    }

    public List<CandidatureVO> getConvocations() {
	if (convocations == null || convocations.isEmpty()) {
	    convocationsMap = convocationService.loadTaskList();
	    convocations = new ArrayList<CandidatureVO>(convocationsMap.keySet());
	}
	return convocations;
    }

    public void setConvocations(List<CandidatureVO> convocations) {
	this.convocations = convocations;
    }

    public Map<CandidatureVO, Task> getConvocationsMap() {
	return convocationsMap;
    }

    public void setConvocationsMap(Map<CandidatureVO, Task> convocationsMap) {
	this.convocationsMap = convocationsMap;
    }

    public CandidatureVO getConvocation() {
	return convocation;
    }

    public void setConvocation(CandidatureVO convocation) {
	this.convocation = convocation;
    }

    public List<CandidatureVO> getStartedconvocations() {
	if (startedconvocations == null || startedconvocations.isEmpty()) {
	    convocationsMap = convocationService.loadStartedTaskList();
	    startedconvocations = new ArrayList<CandidatureVO>(convocationsMap.keySet());
	}
	return startedconvocations;
    }

    public void setStartedconvocations(List<CandidatureVO> startedconvocations) {
	this.startedconvocations = startedconvocations;
    }

    public EntretienVO getEntretien() {
	return entretien;
    }

    public void setEntretien(EntretienVO entretien) {
	this.entretien = entretien;
    }

    public void initEntretien() {
	entretien = new EntretienVO();
    }

    public void initConvocation() {
	convocation = new CandidatureVO();
    }

}
