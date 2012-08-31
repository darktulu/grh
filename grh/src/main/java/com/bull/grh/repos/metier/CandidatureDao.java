package com.bull.grh.repos.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.types.EtatCandidature;

public interface CandidatureDao extends JpaRepository<Candidature, Long> {

    @Query("FROM Candidature c WHERE c.demande.id = :idDemande ")
    public List<Candidature> findByDemand(@Param("idDemande") Long idDemande);

    @Query("SELECT count(*) FROM Candidature c WHERE c.demande.id = :idDemande ")
    public Long findByDemandeIdCount(@Param("idDemande") Long idDemande);

    public List<Candidature> findByEtatCandidatureAndCandidatEmail(EtatCandidature etatCandidature, String email);

    public List<Candidature> findByCandidat(Candidat candidat);

    public List<Candidature> findByDemandeIdAndCandidatUsername(Long id, String username);

}