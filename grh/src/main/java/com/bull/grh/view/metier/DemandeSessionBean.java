package com.bull.grh.view.metier;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.i18n.I18nMessageBean;
import com.bull.grh.service.exception.AlreadyHaveCandidatureException;
import com.bull.grh.service.exception.CandidatureNotFoundException;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.DemandeVO;

@Component
@Scope("session")
public class DemandeSessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DemandeService demandeService;

	@Inject
	private I18nMessageBean i18nMessageBean;

	private DemandeVO demande = new DemandeVO();
	private CandidatVO candidat = new CandidatVO();
	private long countCandidats;

	public void init() {
		demande = new DemandeVO();
		candidat = new CandidatVO();
	}

	public void addCandidat() {
		try {
			demandeService.addCandidateToDemand(candidat, demande);
		} catch (AlreadyHaveCandidatureException e) {
			// TODO Faces message
			i18nMessageBean.showErrorMessage("impossible d'ajouter dandidat");
		}
	}

	public void removeCandidat() {
		try {
			demandeService.removeCandidateFromDemand(candidat, demande);
		} catch (CandidatureNotFoundException e) {
			// TODO Faces message
			i18nMessageBean
					.showErrorMessage("impossible de supprimer dandidat");
		}
	}

	public DemandeService getDemandeService() {
		return demandeService;
	}

	public void setDemandeService(DemandeService demandeService) {
		this.demandeService = demandeService;
	}

	public DemandeVO getDemande() {
		return demande;
	}

	public void setDemande(DemandeVO demande) {
		this.demande = demande;
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

}
