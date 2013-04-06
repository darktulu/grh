package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EtatEntretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntretienDao extends JpaRepository<Entretien, Long> {
    // Search an interview by EvaluationID and Person ID
    List<Entretien> findByCandidatureDemandeIdAndPersonneUsername(Long id, String username);

    List<Entretien> findByCandidatureDemandeId(long demandId);

    Entretien findByCandidatureCandidatUsername(String username);

    List<Entretien> findByPersonneUsername(String username);

    List<Entretien> findByPersonneUsernameAndEtat(String username, EtatEntretien etat);

    @Query("SELECT count(*) FROM Entretien e WHERE e.personne.username = :username AND e.etat = :etat")
    Long getEntretiensCount(@Param("username") String username, @Param("etat") EtatEntretien etat);
}
