package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceDao extends JpaRepository<Experience, Long> {
    List<Experience> findByCandidatUsername(String username);
}
