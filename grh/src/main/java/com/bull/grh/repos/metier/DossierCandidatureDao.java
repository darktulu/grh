package com.bull.grh.repos.metier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.DossierCandidature;

@Repository("dossierCandidatureDao")
public interface DossierCandidatureDao extends
		JpaRepository<DossierCandidature, Long> {

	public DossierCandidature findByCandidatureCandidatUsernameAndCandidatureDemandeId(
			long idCandidat, long idDemande);

	public DossierCandidature findByCandidature(Candidature candidature);
}
