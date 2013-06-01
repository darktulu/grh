package com.bull.grh.service.metier;

import com.bull.grh.service.exception.*;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;

import javax.validation.Valid;
import java.util.List;

public interface DemandeService {
    void createDemande(@Valid DemandeVO demande);

    void startTaskDemandeOP(@Valid DemandeVO demande) throws ServiceException;

    void startTaskDemandeRH(@Valid DemandeVO demande) throws ServiceException;

    List<DemandeVO> loadDemandesNouveau();

    List<DemandeVO> loadDemandesTraite();

    List<DemandeVO> loadStartedDemandesTraite();

    List<DemandeVO> loadDemandesSoumise();

    List<DemandeVO> loadStartedDemandesSoumise();

    Integer getCountDemandesTraite();

    Integer getCountDemandesSoumise();

    Integer getCountStartedDemandesSoumise();

    Integer getCountDemandesNouveau();

    void sendDemande(@Valid DemandeVO demande);

    void deleteDemande(DemandeVO demande);

    void rejectDemande(DemandeVO demande) throws ServiceException;

    void completeDemande(@Valid DemandeVO demande) throws DemandeHaveNoCandidatureException, ServiceException;

    void completeDemande(@Valid DemandeVO demande, List<CandidatureVO> candidatureList)
            throws DemandeHaveNoCandidatureException, ServiceException;

    void addCandidateToDemand(CandidatVO candidat, DemandeVO demand) throws AlreadyHaveCandidatureException,
            NoDemandeSelectedException;

    void removeCandidateFromDemand(CandidatVO candidat, DemandeVO demand) throws CandidatureNotFoundException;

    List<CandidatureVO> loadCandidatures(DemandeVO demande);

    void rejectDemandeAfterAccepting(DemandeVO demande) throws ServiceException;

    Long getCountCandidatures(DemandeVO demande);

    Integer getCountStartedDemandesTraite();
}
