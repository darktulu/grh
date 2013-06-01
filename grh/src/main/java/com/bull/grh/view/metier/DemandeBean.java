package com.bull.grh.view.metier;

import com.bull.grh.domaine.types.EtatDemande;
import com.bull.grh.service.exception.DemandeHaveNoCandidatureException;
import com.bull.grh.service.exception.ServiceException;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;
import com.bull.grh.view.utils.MessagesBean;
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
    @Inject
    transient private MessagesBean messagesBean;

    private List<DemandeVO> demandesTraite;
    private List<DemandeVO> startedDemandesTraite;
    private List<DemandeVO> demandesNouveau;
    private List<DemandeVO> demandesRH;
    private List<DemandeVO> startedDemandesRH;
    private DemandeVO demande = new DemandeVO();
    private List<CandidatureVO> candidatureList;

    public void createDemande() {
        try {
            demandeService.createDemande(demande);
            demande = new DemandeVO();
            messagesBean.showMessage(MessagesBean.MessageType.SUCCESS, "general.success.title", "demande.create.succes");
        } catch (Exception e) {
            messagesBean.showMessage(MessagesBean.MessageType.ERROR, "general.failure.title", "demande.create.faillure");
        }
    }

    public void sendDemande() {
        try {
            demandeService.sendDemande(demande);
            demandesNouveau.remove(demande);
            messagesBean.showGeneralMessage(MessagesBean.MessageType.SUCCESS);
        } catch (Exception e) {
            messagesBean.showGeneralMessage(MessagesBean.MessageType.ERROR);
        }
    }

    public void deleteDemande() {
        try {
            demandeService.deleteDemande(demande);
            demandesNouveau.remove(demande);
            messagesBean.showGeneralMessage(MessagesBean.MessageType.SUCCESS);
        } catch (Exception e) {
            messagesBean.showGeneralMessage(MessagesBean.MessageType.ERROR);
        }
    }

    public void rejectDemande() {
        try {
            demandeService.rejectDemande(demande);
            demandesRH.remove(demande);
            messagesBean.showGeneralMessage(MessagesBean.MessageType.SUCCESS);
        } catch (ServiceException e) {
            messagesBean.showGeneralMessage(MessagesBean.MessageType.ERROR);
        }
    }

    public void rejectDemandeAfterAccepting() {
        try {
            demandeService.rejectDemandeAfterAccepting(demande);
            startedDemandesRH.remove(demande);
            messagesBean.showGeneralMessage(MessagesBean.MessageType.SUCCESS);
        } catch (ServiceException e) {
            messagesBean.showGeneralMessage(MessagesBean.MessageType.ERROR);
        }
    }

    public void proceedDemandeOP() {
        try {
            demandeService.startTaskDemandeOP(demande);
            demandesTraite.remove(demande);
            messagesBean.showGeneralMessage(MessagesBean.MessageType.SUCCESS);
        } catch (ServiceException e) {
            messagesBean.showGeneralMessage(MessagesBean.MessageType.ERROR);
        }
    }

    public void completeDemandeOP() {
        try {
            demandeService.completeDemande(demande, candidatureList);
            startedDemandesTraite.remove(demande);
            messagesBean.showGeneralMessage(MessagesBean.MessageType.SUCCESS);
        } catch (DemandeHaveNoCandidatureException e) {
            // TODO faces message
            messagesBean.showMessageNatif(MessagesBean.MessageType.ERROR, "", "DemandeHaveNoCandidatureException");
        } catch (ServiceException e) {
            messagesBean.showGeneralMessage(MessagesBean.MessageType.ERROR);
        }
    }

    public void proceedDemandeRH() {
        try {
            demandeService.startTaskDemandeRH(demande);
            demandesRH.remove(demande);
            messagesBean.showGeneralMessage(MessagesBean.MessageType.SUCCESS);
        } catch (ServiceException e) {
            messagesBean.showGeneralMessage(MessagesBean.MessageType.ERROR);
        }
    }

    public void completeDemandeRH() {
        try {
            demandeService.completeDemande(demande);
            startedDemandesRH.remove(demande);
            messagesBean.showGeneralMessage(MessagesBean.MessageType.SUCCESS);
        } catch (DemandeHaveNoCandidatureException e) {
            // TODO faces message
            messagesBean.showMessageNatif(MessagesBean.MessageType.ERROR, "", "DemandeHaveNoCandidatureException");
        } catch (ServiceException e) {
            messagesBean.showGeneralMessage(MessagesBean.MessageType.ERROR);
        }
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
            startedDemandesTraite = demandeService.loadStartedDemandesTraite();
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
