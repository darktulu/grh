package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationDao extends JpaRepository<Formation, Long> {
    List<Formation> findByCandidat_username(String username);
}
