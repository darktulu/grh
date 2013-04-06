package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CandidatDao extends JpaRepository<Candidat, String>, JpaSpecificationExecutor<Candidat> {
    Candidat findByEmail(String email);

    Candidat findByUsername(String username);
}
