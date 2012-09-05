package com.bull.grh.repos.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Langue;

@Repository("langueDao")
public interface LangueDao extends JpaRepository<Langue, Long> {

    public List<Langue> findByCandidat_username(String username);
}
