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

    void createDemande(@Valid DemandeVO demande);

    void startTaskDemandeOP(Task task, @Valid DemandeVO demande);

    void startTaskDemandeRH(Task task, @Valid DemandeVO demande);

    Map<DemandeVO, Task> loadDemandesNouveau();

    Map<DemandeVO, Task> loadDemandesTraite();

    Map<DemandeVO, Task> loadDemandesSoumise();

    Map<DemandeVO, Task> loadStartedDemandesTraite();

    Map<DemandeVO, Task> loadStartedDemandesSoumise();

    Long getCountDemandesTraite();

    Long getCountDemandesSoumise();

    Long getCountStartedDemandesTraite();

    Long getCountStartedDemandesSoumise();

    Long getCountDemandesNouveau();

    void sendDemande(Task task, @Valid DemandeVO demande);

    void deleteDemande(Task task, DemandeVO demande);

    void rejectDemande(Task task, DemandeVO demande);

    void completeDemande(Task task, @Valid DemandeVO demande) throws DemandeHaveNoCandidatureException;

    void completeDemande(Task task, @Valid DemandeVO demande, List<CandidatureVO> candidatureList)
	    throws DemandeHaveNoCandidatureException;

    void addCandidateToDemand(CandidatVO candidat, DemandeVO demand) throws AlreadyHaveCandidatureException;

    void removeCandidateFromDemand(CandidatVO candidat, DemandeVO demand) throws CandidatureNotFoundException;

    List<CandidatureVO> loadCandidatures(DemandeVO demande);

    void rejectDemandeAfterAccepting(Task task, DemandeVO demande);

    Long getCountCandidatures(DemandeVO demande);

}
