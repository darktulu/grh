package com.bull.grh.repos.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.ValueList;

@Repository("valueListDao")
public interface ValueListDao extends JpaRepository<ValueList, Long> {

    public ValueList findByValue(String value);

    public List<ValueList> findByParent(long id);

    public List<ValueList> findByParentIsNull();

    public List<ValueList> findByParent_value(String parentNom);

    public List<ValueList> findByTypeValue_nom(String nom);

}
