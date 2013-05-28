package com.bull.grh.service.metier;

import com.bull.grh.service.exception.AlreadyHaveCandidatureException;
import com.bull.grh.service.exception.CandidatureNotFoundException;
import com.bull.grh.service.exception.DemandeHaveNoCandidatureException;
import com.bull.grh.service.exception.NoDemandeSelectedException;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

public interface DemandeService {

    void createDemande(@Valid DemandeVO demande);

    void startTaskDemandeOP(@Valid DemandeVO demande);

    void startTaskDemandeRH(@Valid DemandeVO demande);

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

    void rejectDemande(DemandeVO demande);

    void completeDemande(@Valid DemandeVO demande) throws DemandeHaveNoCandidatureException;

    void completeDemande(@Valid DemandeVO demande, List<CandidatureVO> candidatureList)
            throws DemandeHaveNoCandidatureException;

    void addCandidateToDemand(CandidatVO candidat, DemandeVO demand) throws AlreadyHaveCandidatureException, NoDemandeSelectedException;

    void removeCandidateFromDemand(CandidatVO candidat, DemandeVO demand) throws CandidatureNotFoundException;

    List<CandidatureVO> loadCandidatures(DemandeVO demande);

    void rejectDemandeAfterAccepting(DemandeVO demande);

    Long getCountCandidatures(DemandeVO demande);

    @Transactional(readOnly = true)
    Integer getCountStartedDemandesTraite();
}
