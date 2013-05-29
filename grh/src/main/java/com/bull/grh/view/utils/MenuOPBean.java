package com.bull.grh.view.utils;

import com.bull.grh.service.metier.DemandeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;

@Component
@Scope("request")
public class MenuOPBean implements Serializable {
    @Inject
    private DemandeService demandeService;

    private Integer demandesCount, demandesTraiteCount, starteddemandeTraiteCount;

    public Integer getDemandesCount() {
        demandesCount = demandeService.getCountDemandesNouveau();
        return demandesCount;
    }

    public void setDemandesCount(Integer demandesCount) {
        this.demandesCount = demandesCount;
    }

    public Integer getDemandesTraiteCount() {
        demandesTraiteCount = demandeService.getCountDemandesTraite();
        return demandesTraiteCount;
    }

    public void setDemandesTraiteCount(Integer demandesTraiteCount) {
        this.demandesTraiteCount = demandesTraiteCount;
    }

    public Integer getStarteddemandeTraiteCount() {
        starteddemandeTraiteCount = demandeService.getCountStartedDemandesTraite();
        return starteddemandeTraiteCount;
    }

    public void setStarteddemandeTraiteCount(Integer starteddemandeTraiteCount) {
        this.starteddemandeTraiteCount = starteddemandeTraiteCount;
    }
}
