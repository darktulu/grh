package com.bull.grh.view.metier;

import com.bull.grh.service.exception.DemandeHaveNoCandidatureException;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Component
@Scope("view")
public class DemandeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private DemandeService demandeService;

    private List<DemandeVO> demandesTraite;
    private List<DemandeVO> startedDemandesTraite;
    private List<DemandeVO> demandesNouveau;
    private List<DemandeVO> demandesRH;
    private List<DemandeVO> startedDemandesRH;
    private DemandeVO demande = new DemandeVO();
    private List<CandidatureVO> candidatureList;

    public void createDemande() {
        demandeService.createDemande(demande);
    }

    public void sendDemande() {
        demandeService.sendDemande(demande);
        demandesNouveau.remove(demande);
    }

    public void deleteDemande() {
        demandeService.deleteDemande(demande);
        demandesNouveau.remove(demande);
    }

    public void rejectDemande() {
        demandeService.rejectDemande(demande);
        demandesRH.remove(demande);
    }

    public void rejectDemandeAfterAccepting() {
        demandeService.rejectDemandeAfterAccepting(demande);
        startedDemandesRH.remove(demande);
    }

    public void proceedDemandeOP() {
        demandeService.startTaskDemandeOP(demande);
        demandesTraite.remove(demande);
    }

    public void completeDemandeOP() {
        try {
            demandeService.completeDemande(demande, candidatureList);
            startedDemandesTraite.remove(demande);
        } catch (DemandeHaveNoCandidatureException e) {
            // TODO faces message
        }
    }

    public void proceedDemandeRH() {
        demandeService.startTaskDemandeRH(demande);
        demandesRH.remove(demande);
    }

    public void completeDemandeRH() {
        try {
            demandeService.completeDemande(demande);
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
            demandesTraite = demandeService.loadDemandesTraite();
        }
        return demandesTraite;
    }

    public void setDemandesTraite(List<DemandeVO> demandesTraite) {
        this.demandesTraite = demandesTraite;
    }

    public List<DemandeVO> getStartedDemandesTraite() {
        if (startedDemandesTraite == null || startedDemandesTraite.isEmpty()) {
            startedDemandesTraite = demandeService.loadDemandesTraite();
        }
        return startedDemandesTraite;
    }

    public void setStartedDemandesTraite(List<DemandeVO> startedDemandesTraite) {
        this.startedDemandesTraite = startedDemandesTraite;
    }

    public List<DemandeVO> getDemandesNouveau() {
        if (demandesNouveau == null || demandesNouveau.isEmpty()) {
            demandesNouveau = demandeService.loadDemandesNouveau();
        }
        return demandesNouveau;
    }

    public void setDemandesNouveau(List<DemandeVO> demandesNouveau) {
        this.demandesNouveau = demandesNouveau;
    }

    public List<DemandeVO> getDemandesRH() {
        if (demandesRH == null || demandesRH.isEmpty()) {
            demandesRH = demandeService.loadDemandesSoumise();
        }
        return demandesRH;
    }

    public void setDemandesRH(List<DemandeVO> demandesRH) {
        this.demandesRH = demandesRH;
    }

    public List<DemandeVO> getStartedDemandesRH() {
        if (startedDemandesRH == null || startedDemandesRH.isEmpty()) {
            startedDemandesRH = demandeService.loadStartedDemandesSoumise();
        }
        return startedDemandesRH;
    }

    public void setStartedDemandesRH(List<DemandeVO> startedDemandesRH) {
        this.startedDemandesRH = startedDemandesRH;
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
