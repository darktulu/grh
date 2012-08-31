package com.bull.grh.repos.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.RefField;

@Repository("refFieldDao")
public interface RefFieldDao extends JpaRepository<RefField, Long> {

    public List<RefField> findByformulaire(String formType);

    public List<RefField> findByNomAndFormulaire(String nom, String formType);

    public List<RefField> findByNom(String nom);
}
