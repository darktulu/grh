package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.types.EtatCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatureDao extends JpaRepository<Candidature, Long> {
    List<Candidature> findByDemandeId(Long idDemande);

    @Query("SELECT count(c) FROM Candidature c WHERE c.demande.id = :idDemande ")
    Long findByDemandeIdCount(@Param("idDemande") Long idDemande);

    List<Candidature> findByEtatCandidatureAndCandidatEmail(EtatCandidature etatCandidature, String email);

    List<Candidature> findByCandidat(Candidat candidat);

    List<Candidature> findByDemandeIdAndCandidatUsername(Long id, String username);
}