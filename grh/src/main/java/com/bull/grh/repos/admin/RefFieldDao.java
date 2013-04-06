package com.bull.grh.repos.admin;

import com.bull.grh.domaine.RefField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefFieldDao extends JpaRepository<RefField, Long> {
    List<RefField> findByformulaire(String formType);

    List<RefField> findByNomAndFormulaire(String nom, String formType);

    List<RefField> findByNom(String nom);
}
