package com.bull.grh.repos.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Competence;

@Repository("competenceDao")
public interface CompetenceDao extends JpaRepository<Competence, Long> {

    public List<Competence> findByCandidat_username(String username);
}
