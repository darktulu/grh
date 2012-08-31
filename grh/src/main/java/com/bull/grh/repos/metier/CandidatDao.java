package com.bull.grh.repos.metier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Candidat;

@Repository("candidatDao")
public interface CandidatDao extends JpaRepository<Candidat, String>, JpaSpecificationExecutor<Candidat> {

    public Candidat findByEmail(String email);

    public Candidat findByUsername(String username);

}
