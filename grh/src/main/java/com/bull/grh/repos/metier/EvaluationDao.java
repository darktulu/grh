package com.bull.grh.repos.metier;

import com.bull.grh.domaine.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationDao extends JpaRepository<Evaluation, Long> {
}
