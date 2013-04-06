package com.bull.grh.repos.admin;

import com.bull.grh.domaine.ValueList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValueListDao extends JpaRepository<ValueList, Long> {
    ValueList findByValue(String value);

    List<ValueList> findByParent(long id);

    List<ValueList> findByParentIsNull();

    List<ValueList> findByParent_value(String parentNom);

    List<ValueList> findByTypeValue_nom(String nom);
}
