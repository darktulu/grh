package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.DossierCandidature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierCandidatureDao extends
        JpaRepository<DossierCandidature, Long> {
    DossierCandidature findByCandidatureCandidatUsernameAndCandidatureDemandeId(
            long idCandidat, long idDemande);

    DossierCandidature findByCandidature(Candidature candidature);
}
