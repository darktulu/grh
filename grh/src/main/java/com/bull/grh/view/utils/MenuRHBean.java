package com.bull.grh.view.utils;

import com.bull.grh.service.metier.ConvocationService;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.service.metier.EntretienService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;

@Component
@Scope("request")
public class MenuRHBean implements Serializable {
    @Inject
    private EntretienService entretienService;
    @Inject
    private DemandeService demandeService;
    @Inject
    private ConvocationService convocationsService;

    private Integer entretiensCount, startedentretiensCount, convocationsCount, startedconvocationsCount, demandesCount,
            starteddemandesCount;

    public Integer getConvocationsCount() {
        convocationsCount = convocationsService.getCountTaskList();
        return convocationsCount;
    }

    public void setConvocationsCount(Integer convocationsCount) {
        this.convocationsCount = convocationsCount;
    }

    public Integer getStartedconvocationsCount() {
        startedconvocationsCount = convocationsService.getCountStartedTaskList();
        return startedconvocationsCount;
    }

    public void setStartedconvocationsCount(Integer startedconvocationsCount) {
        this.startedconvocationsCount = startedconvocationsCount;
    }

    public Integer getDemandesCount() {
        demandesCount = demandeService.getCountDemandesSoumise();
        return demandesCount;
    }

    public void setDemandesCount(Integer demandesCount) {
        this.demandesCount = demandesCount;
    }

    public Integer getStarteddemandesCount() {
        starteddemandesCount = demandeService.getCountStartedDemandesSoumise();
        return starteddemandesCount;
    }

    public void setStarteddemandesCount(Integer starteddemandesCount) {
        this.starteddemandesCount = starteddemandesCount;
    }

    public Integer getEntretiensCount() {
        entretiensCount = entretienService.getCountRHTaskList();
        return entretiensCount;
    }

    public void setEntretiensCount(Integer entretiensCount) {
        this.entretiensCount = entretiensCount;
    }

    public Integer getStartedentretiensCount() {
        startedentretiensCount = entretienService.getCountRHStartedTaskList();
        return startedentretiensCount;
    }

    public void setStartedentretiensCount(Integer startedentretiensCount) {
        this.startedentretiensCount = startedentretiensCount;
    }
}
