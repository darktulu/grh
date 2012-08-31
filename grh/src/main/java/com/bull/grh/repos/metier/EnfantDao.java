package com.bull.grh.repos.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.DossierCandidature;
import com.bull.grh.domaine.Enfant;

@Repository("enfantDao")
public interface EnfantDao extends JpaRepository<Enfant, Long> {
	
	public List<Enfant> findByDossierCandidature(
			DossierCandidature dossierCandidature);
}
