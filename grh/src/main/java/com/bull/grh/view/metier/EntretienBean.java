package com.bull.grh.view.metier;

import com.bull.grh.service.metier.EntretienService;
import com.bull.grh.view.metier.vo.EntretienVO;
import com.bull.grh.view.metier.vo.EvaluationVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Scope("view")
@Component
public class EntretienBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private EntretienService entretienService;

    private List<EntretienVO> entretiens, entretiensRH, entretiensTask, startedentretiensRH;
    private EntretienVO entretien = new EntretienVO();
    private EvaluationVO evaluation = new EvaluationVO();

    public void proceedEntretienCE() {
        entretienService.startProcess(entretien);
        entretiens.remove(entretien);
    }

    public void completeEntretienCE() {
        entretien.setEvaluation(evaluation);
        entretienService.startAndCompleteTask(entretien);
    }

    public void proceedEntretienRH() {
        entretienService.startRHTask(entretien);
    }

    public void completeEntretienRH() {
        entretien.setEvaluation(evaluation);
        entretienService.completeTaskRH(entretien);
    }

    public List<EntretienVO> getEntretiensRH() {
        if (entretiensRH == null || entretiensRH.isEmpty()) {
            entretiensRH = entretienService.loadRHTaskList();
        }
        return entretiensRH;
    }

    public void setEntretiensRH(List<EntretienVO> entretiensRH) {
        this.entretiensRH = entretiensRH;
    }

    public List<EntretienVO> getStartedentretiensRH() {
        if (startedentretiensRH == null || startedentretiensRH.isEmpty()) {
            startedentretiensRH = entretienService.loadRHStartedTaskList();
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
            entretiensTask = entretienService.loadCETaskList();
        }
        return entretiensTask;
    }

    public void setEntretiensTask(List<EntretienVO> startedentretiens) {
        this.entretiensTask = startedentretiens;
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
