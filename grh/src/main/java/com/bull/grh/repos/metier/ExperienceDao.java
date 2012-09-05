package com.bull.grh.repos.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Experience;

@Repository("experienceDao")
public interface ExperienceDao extends JpaRepository<Experience, Long> {

    public List<Experience> findByCandidat_username(String username);
}
