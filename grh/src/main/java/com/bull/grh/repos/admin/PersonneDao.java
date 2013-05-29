package com.bull.grh.repos.admin;

import com.bull.grh.domaine.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneDao extends JpaRepository<Personne, Long> {
    Personne findByUsername(String username);
}
