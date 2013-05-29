package com.bull.grh.view.utils;

import com.bull.grh.service.metier.EntretienService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;

@Component
@Scope("request")
public class MenuCEBean implements Serializable {
    @Inject
    private EntretienService entretienService;

    private long entretiensCount, startedentretiensCount;

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
