package com.bull.grh.repos.admin;

import com.bull.grh.domaine.TypeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface TypeValueDao extends JpaRepository<TypeValue, Serializable> {
    TypeValue findByNom(String nom);

    List<TypeValue> findByParentIsNull();

    List<TypeValue> findByParent_nom(String nom);

    @Modifying
    @Query("update TypeValue t set t.nom = ?1 where t.id= ?2")
    void updateType(String nom, Long id);
}
