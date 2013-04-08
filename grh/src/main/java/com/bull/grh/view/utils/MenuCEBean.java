package com.bull.grh.view.utils;

import com.bull.grh.service.metier.EntretienService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;

@Component
@Scope("request")
public class MenuCEBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntretienService entretienService;

    private long entretiensCount, startedentretiensCount;

    public EntretienService getEntretienService() {
        return entretienService;
    }

    public void setEntretienService(EntretienService entretienService) {
        this.entretienService = entretienService;
    }

    public long getEntretiensCount() {
        entretiensCount = entretienService.getCountCEEntretiens();
        return entretiensCount;
    }

    public void setEntretiensCount(long entretiensCount) {
        this.entretiensCount = entretiensCount;
    }

    public long getStartedentretiensCount() {
        startedentretiensCount = entretienService.getCountCETaskList();
        return startedentretiensCount;
    }

    public void setStartedentretiensCount(long startedentretiensCount) {
        this.startedentretiensCount = startedentretiensCount;
    }

}
