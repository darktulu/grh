package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Langue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LangueDao extends JpaRepository<Langue, Long> {
    List<Langue> findByCandidat_username(String username);
}
