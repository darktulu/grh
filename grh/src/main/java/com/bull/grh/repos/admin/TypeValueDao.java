package com.bull.grh.repos.admin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.TypeValue;

@Repository("typeValueDao")
public interface TypeValueDao extends JpaRepository<TypeValue, Serializable> {

    public TypeValue findByNom(String nom);

    public List<TypeValue> findByParentIsNull();

    public List<TypeValue> findByParent_nom(String nom);

    @Modifying
    @Query("update TypeValue t set t.nom = ?1 where t.id= ?2")
    public void updateType(String nom, Long id);

}
