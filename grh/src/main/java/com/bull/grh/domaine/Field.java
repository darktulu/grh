package com.bull.grh.domaine;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Field {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String value;

    @ManyToOne
    private DynamicForm dynamicForm;

    @ManyToOne
    private RefField refField;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DynamicForm getDynamicForm() {
        return dynamicForm;
    }

    public void setDynamicForm(DynamicForm dynamicForm) {
        this.dynamicForm = dynamicForm;
    }

    public RefField getRefField() {
        return refField;
    }

    public void setRefField(RefField refField) {
        this.refField = refField;
    }


}