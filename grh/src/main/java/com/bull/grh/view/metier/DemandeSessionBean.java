package com.bull.grh.view.metier;

import com.bull.grh.service.exception.AlreadyHaveCandidatureException;
import com.bull.grh.service.exception.CandidatureNotFoundException;
import com.bull.grh.service.exception.NoDemandeSelectedException;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.DemandeVO;
import com.bull.grh.view.utils.MessagesBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;

@Component
@Scope("session")
public class DemandeSessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DemandeService demandeService;
    @Inject
    private MessagesBean messagesBean;

    private DemandeVO demande = new DemandeVO();
    private CandidatVO candidat = new CandidatVO();
    private long countCandidats;
    private Boolean changed = false;

    public void init() {
        demande = new DemandeVO();
        candidat = new CandidatVO();
    }

    public void addCandidat() {
        try {
            demandeService.addCandidateToDemand(candidat, demande);
            changed = true;
        } catch (AlreadyHaveCandidatureException e) {
            messagesBean.showMessageNatif(MessagesBean.MessageType.ERROR, "", "impossible d'ajouter dandidat");
        } catch (NoDemandeSelectedException e) {
            messagesBean.showMessageNatif(MessagesBean.MessageType.ERROR, "", "Pas de demande selectionnée");
        }

    }

    public void removeCandidat() {
        try {
            demandeService.removeCandidateFromDemand(candidat, demande);
            changed = true;
        } catch (CandidatureNotFoundException e) {
            // TODO Faces message
            messagesBean.showMessageNatif(MessagesBean.MessageType.ERROR, "", "impossible de supprimer dandidat");
        }
    }

    public void reset() {
        changed = false;
    }

    public DemandeVO getDemande() {
        return demande;
    }

    public void setDemande(DemandeVO demande) {
        this.demande = demande;
        changed = true;
    }

    public CandidatVO getCandidat() {
        return candidat;
    }

    public void setCandidat(CandidatVO candidat) {
        this.candidat = candidat;
    }

    public long getCountCandidats() {
        countCandidats = demandeService.getCountCandidatures(demande);
        return countCandidats;
    }

    public void setCountCandidats(long countCandidats) {
        this.countCandidats = countCandidats;
    }

    public Boolean getChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
    }
}
