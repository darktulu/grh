package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EtatEntretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntretienDao extends JpaRepository<Entretien, Long> {
    List<Entretien> findByPersonneUsernameAndEtat(String username, EtatEntretien etat);

    @Query("SELECT count(e) FROM Entretien e WHERE e.personne.username = :username AND e.etat = :etat")
    Long getEntretiensCount(@Param("username") String username, @Param("etat") EtatEntretien etat);
}
