package com.bull.grh.repos.metier;

import com.bull.grh.domaine.DossierCandidature;
import com.bull.grh.domaine.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnfantDao extends JpaRepository<Enfant, Long> {
    List<Enfant> findByDossierCandidature(DossierCandidature dossierCandidature);
}
