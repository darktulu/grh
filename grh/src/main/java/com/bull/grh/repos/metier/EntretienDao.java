package com.bull.grh.repos.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EtatEntretien;

@Repository("entretienDao")
public interface EntretienDao extends JpaRepository<Entretien, Long> {

	// Search an interview by EvaluationID and Person ID
	public List<Entretien> findByCandidatureDemandeIdAndPersonneUsername(Long id, String username);

	public List<Entretien> findByCandidatureDemandeId(long demandId);

	public Entretien findByCandidatureCandidatUsername(String username);

	public List<Entretien> findByPersonneUsername(String username);

	public List<Entretien> findByPersonneUsernameAndEtat(String username, EtatEntretien etat);

	@Query("SELECT count(*) FROM Entretien e WHERE e.personne.username = :username AND e.etat = :etat")
	public Long getEntretiensCount(@Param("username") String username, @Param("etat") EtatEntretien etat);

}
