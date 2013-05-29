package com.bull.grh.repos.admin;

import com.bull.grh.domaine.TypeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface TypeValueDao extends JpaRepository<TypeValue, Serializable> {
    TypeValue findByNom(String nom);

    List<TypeValue> findByParentIsNull();

    List<TypeValue> findByParentNom(String nom);
}
