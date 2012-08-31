package com.bull.grh.view.utils;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.service.metier.ConvocationService;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.service.metier.EntretienService;

@Component
@Scope("request")
public class MenuRHBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntretienService entretienService;

    @Inject
    private DemandeService demandeService;

    @Inject
    private ConvocationService convocationsService;

    private long entretiensCount, startedentretiensCount, convocationsCount, startedconvocationsCount, demandesCount,
	    starteddemandesCount;

    public long getConvocationsCount() {
	convocationsCount = convocationsService.getCountTaskList();
	return convocationsCount;
    }

    public void setConvocationsCount(long convocationsCount) {
	this.convocationsCount = convocationsCount;
    }

    public long getStartedconvocationsCount() {
	startedconvocationsCount = convocationsService.getCountStartedTaskList();
	return startedconvocationsCount;
    }

    public void setStartedconvocationsCount(long startedconvocationsCount) {
	this.startedconvocationsCount = startedconvocationsCount;
    }

    public long getDemandesCount() {
	demandesCount = demandeService.getCountDemandesSoumise();
	return demandesCount;
    }

    public void setDemandesCount(long demandesCount) {
	this.demandesCount = demandesCount;
    }

    public long getStarteddemandesCount() {
	starteddemandesCount = demandeService.getCountStartedDemandesSoumise();
	return starteddemandesCount;
    }

    public void setStarteddemandesCount(long starteddemandesCount) {
	this.starteddemandesCount = starteddemandesCount;
    }

    public EntretienService getEntretienService() {
	return entretienService;
    }

    public void setEntretienService(EntretienService entretienService) {
	this.entretienService = entretienService;
    }

    public long getEntretiensCount() {
	entretiensCount = entretienService.getCountRHTaskList();
	return entretiensCount;
    }

    public void setEntretiensCount(long entretiensCount) {
	this.entretiensCount = entretiensCount;
    }

    public long getStartedentretiensCount() {
	startedentretiensCount = entretienService.getCountRHStartedTaskList();
	return startedentretiensCount;
    }

    public void setStartedentretiensCount(long startedentretiensCount) {
	this.startedentretiensCount = startedentretiensCount;
    }

    public DemandeService getDemandeService() {
	return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
	this.demandeService = demandeService;
    }

    public ConvocationService getConvocationsService() {
	return convocationsService;
    }

    public void setConvocationsService(ConvocationService convocationsService) {
	this.convocationsService = convocationsService;
    }

}
