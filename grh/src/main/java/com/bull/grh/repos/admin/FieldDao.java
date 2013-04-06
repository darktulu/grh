package com.bull.grh.repos.admin;

import com.bull.grh.domaine.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldDao extends JpaRepository<Field, Long> {
    List<Field> findByDynamicFormId(long id);

    Field findByDynamicFormIdAndRefFieldId(long dynamicformId, long refId);

    List<Field> findByRefFieldId(long refId);
}
