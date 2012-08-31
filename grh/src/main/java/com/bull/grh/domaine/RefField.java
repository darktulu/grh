package com.bull.grh.domaine;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bull.grh.domaine.types.JavaType;
import com.bull.grh.domaine.types.Regexp;

@Entity
public class RefField {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    @Enumerated
    private JavaType javaType;
    @Enumerated
    private Regexp regExpr;
    private String formulaire;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public JavaType getJavaType() {
	return javaType;
    }

    public void setJavaType(JavaType javaType) {
	this.javaType = javaType;
    }

    public Regexp getRegExpr() {
	return regExpr;
    }

    public void setRegExpr(Regexp regExpr) {
	this.regExpr = regExpr;
    }

    public String getFormulaire() {
	return formulaire;
    }

    public void setFormulaire(String formulaire) {
	this.formulaire = formulaire;
    }

}