package com.bull.grh.repos.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.DynamicForm;

@Repository("dynamicDao")
public interface DynamicFormDao extends JpaRepository<DynamicForm, Long> {

}
