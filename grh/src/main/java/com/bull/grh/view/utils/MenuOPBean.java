package com.bull.grh.view.utils;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.service.metier.DemandeService;

@Component
@Scope("request")
public class MenuOPBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private DemandeService demandeService;
    
    private long demandesCount, demandesTraiteCount, starteddemandeTraiteCount;

    public DemandeService getDemandeService() {
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public long getDemandesCount() {
	demandesCount = demandeService.getCountDemandesNouveau();
        return demandesCount;
    }

    public void setDemandesCount(long demandesCount) {
        this.demandesCount = demandesCount;
    }

    public long getDemandesTraiteCount() {
	demandesTraiteCount = demandeService.getCountDemandesTraite();
        return demandesTraiteCount;
    }

    public void setDemandesTraiteCount(long demandesTraiteCount) {
        this.demandesTraiteCount = demandesTraiteCount;
    }

    public long getStarteddemandeTraiteCount() {
	starteddemandeTraiteCount = demandeService.getCountStartedDemandesTraite();
        return starteddemandeTraiteCount;
    }

    public void setStarteddemandeTraiteCount(long starteddemandeTraiteCount) {
        this.starteddemandeTraiteCount = starteddemandeTraiteCount;
    }


}
