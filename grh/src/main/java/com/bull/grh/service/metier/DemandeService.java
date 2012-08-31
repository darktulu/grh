package com.bull.grh.service.metier;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.jbpm.task.Task;

import com.bull.grh.service.exception.AlreadyHaveCandidatureException;
import com.bull.grh.service.exception.CandidatureNotFoundException;
import com.bull.grh.service.exception.DemandeHaveNoCandidatureException;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;

public interface DemandeService {

    public void createDemande(@Valid DemandeVO demande);

    public void startTaskDemandeOP(Task task, @Valid DemandeVO demande);

    public void startTaskDemandeRH(Task task, @Valid DemandeVO demande);

    public Map<DemandeVO, Task> loadDemandesNouveau();

    public Map<DemandeVO, Task> loadDemandesTraite();

    public Map<DemandeVO, Task> loadDemandesSoumise();

    public Map<DemandeVO, Task> loadStartedDemandesTraite();

    public Map<DemandeVO, Task> loadStartedDemandesSoumise();

    public Long getCountDemandesTraite();

    public Long getCountDemandesSoumise();

    public Long getCountStartedDemandesTraite();

    public Long getCountStartedDemandesSoumise();

    public Long getCountDemandesNouveau();

    public void sendDemande(Task task, @Valid DemandeVO demande);

    public void deleteDemande(Task task, DemandeVO demande);

    public void rejectDemande(Task task, DemandeVO demande);

    public void completeDemande(Task task, @Valid DemandeVO demande) throws DemandeHaveNoCandidatureException;

    public void completeDemande(Task task, @Valid DemandeVO demande, List<CandidatureVO> candidatureList)
	    throws DemandeHaveNoCandidatureException;

    public void addCandidateToDemand(CandidatVO candidat, DemandeVO demand) throws AlreadyHaveCandidatureException;

    public void removeCandidateFromDemand(CandidatVO candidat, DemandeVO demand) throws CandidatureNotFoundException;

    public List<CandidatureVO> loadCandidatures(DemandeVO demande);

    public void rejectDemandeAfterAccepting(Task task, DemandeVO demande);

    public Long getCountCandidatures(DemandeVO demande);

}
