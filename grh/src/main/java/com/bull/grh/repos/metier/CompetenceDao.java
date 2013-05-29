package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetenceDao extends JpaRepository<Competence, Long> {
    List<Competence> findByCandidatUsername(String username);
}
