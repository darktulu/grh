package com.bull.grh.repos.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.Field;

@Repository("fieldDao")
public interface FieldDao extends JpaRepository<Field, Long> {

    public List<Field> findByDynamicFormId(long id);

    public Field findByDynamicFormIdAndRefFieldId(long dynamicformId, long refId);

    public List<Field> findByRefFieldId(long refId);

}
