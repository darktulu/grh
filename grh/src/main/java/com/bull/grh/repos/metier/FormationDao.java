package com.bull.grh.repos.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Formation;

@Repository("formationDao")
public interface FormationDao extends JpaRepository<Formation, Long> {
    public List<Formation> findByCandidat_username(String username);
}
