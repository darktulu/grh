package com.bull.grh.repos.metier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Evaluation;

@Repository("evaluationDao")
public interface EvaluationDao extends JpaRepository<Evaluation, Long> {

}
