package com.bull.grh.view.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jbpm.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.service.exception.DemandeHaveNoCandidatureException;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;

@Component
@Scope("view")
public class DemandeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private DemandeService demandeService;

    private List<DemandeVO> demandesTraite, startedDemandesTraite, demandesNouveau, demandesRH, startedDemandesRH;
    private Map<DemandeVO, Task> demandesMap;
    private DemandeVO demande = new DemandeVO();
    private List<CandidatureVO> candidatureList;

    public void createDemande() {
	demandeService.createDemande(demande);
    }

    public void sendDemande() {
	Task task = demandesMap.get(demande);
	demandeService.sendDemande(task, demande);
	demandesNouveau.remove(demande);
    }

    public void deleteDemande() {
	Task task = demandesMap.get(demande);
	demandeService.deleteDemande(task, demande);
	demandesNouveau.remove(demande);
    }

    public void rejectDemande() {
	Task task = demandesMap.get(demande);
	demandeService.rejectDemande(task, demande);
	demandesRH.remove(demande);
    }

    public void rejectDemandeAfterAccepting() {
	Task task = demandesMap.get(demande);
	demandeService.rejectDemandeAfterAccepting(task, demande);
	startedDemandesRH.remove(demande);
    }

    public void proceedDemandeOP() {
	Task task = demandesMap.get(demande);
	demandeService.startTaskDemandeOP(task, demande);
	demandesTraite.remove(demande);
    }

    public void completeDemandeOP() {
	try {
	    Task task = demandesMap.get(demande);
	    demandeService.completeDemande(task, demande, candidatureList);
	    startedDemandesTraite.remove(demande);
	} catch (DemandeHaveNoCandidatureException e) {
	    // TODO faces message
	}
    }

    public void proceedDemandeRH() {
	Task task = demandesMap.get(demande);
	demandeService.startTaskDemandeRH(task, demande);
	demandesRH.remove(demande);
    }

    public void completeDemandeRH() {
	try {
	    Task task = demandesMap.get(demande);
	    demandeService.completeDemande(task, demande);
	    startedDemandesRH.remove(demande);
	} catch (DemandeHaveNoCandidatureException e) {
	    // TODO faces message
	}
    }

    public DemandeService getDemandeService() {
	return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
	this.demandeService = demandeService;
    }

    public List<DemandeVO> getDemandesTraite() {
	if (demandesTraite == null || demandesTraite.isEmpty()) {
	    demandesMap = demandeService.loadDemandesTraite();
	    demandesTraite = new ArrayList<DemandeVO>(demandesMap.keySet());
	}
	return demandesTraite;
    }

    public void setDemandesTraite(List<DemandeVO> demandesTraite) {
	this.demandesTraite = demandesTraite;
    }

    public List<DemandeVO> getStartedDemandesTraite() {
	if (startedDemandesTraite == null || startedDemandesTraite.isEmpty()) {
	    demandesMap = demandeService.loadStartedDemandesTraite();
	    startedDemandesTraite = new ArrayList<DemandeVO>(demandesMap.keySet());
	}
	return startedDemandesTraite;
    }

    public void setStartedDemandesTraite(List<DemandeVO> startedDemandesTraite) {
	this.startedDemandesTraite = startedDemandesTraite;
    }

    public List<DemandeVO> getDemandesNouveau() {
	if (demandesNouveau == null || demandesNouveau.isEmpty()) {
	    demandesMap = demandeService.loadDemandesNouveau();
	    demandesNouveau = new ArrayList<DemandeVO>(demandesMap.keySet());
	}
	return demandesNouveau;
    }

    public void setDemandesNouveau(List<DemandeVO> demandesNouveau) {
	this.demandesNouveau = demandesNouveau;
    }

    public List<DemandeVO> getDemandesRH() {
	if (demandesRH == null || demandesRH.isEmpty()) {
	    demandesMap = demandeService.loadDemandesSoumise();
	    demandesRH = new ArrayList<DemandeVO>(demandesMap.keySet());
	}
	return demandesRH;
    }

    public void setDemandesRH(List<DemandeVO> demandesRH) {
	this.demandesRH = demandesRH;
    }

    public List<DemandeVO> getStartedDemandesRH() {
	if (startedDemandesRH == null || startedDemandesRH.isEmpty()) {
	    demandesMap = demandeService.loadStartedDemandesSoumise();
	    startedDemandesRH = new ArrayList<DemandeVO>(demandesMap.keySet());
	}
	return startedDemandesRH;
    }

    public void setStartedDemandesRH(List<DemandeVO> startedDemandesRH) {
	this.startedDemandesRH = startedDemandesRH;
    }

    public Map<DemandeVO, Task> getDemandesMap() {
	return demandesMap;
    }

    public void setDemandesMap(Map<DemandeVO, Task> demandesMap) {
	this.demandesMap = demandesMap;
    }

    public DemandeVO getDemande() {
	return demande;
    }

    public void setDemande(DemandeVO demande) {
	this.demande = demande;
    }

    public List<CandidatureVO> getCandidatureList() {
	candidatureList = demandeService.loadCandidatures(demande);
	return candidatureList;
    }

    public void setCandidatureList(List<CandidatureVO> candidatureList) {
	this.candidatureList = candidatureList;
    }

}
