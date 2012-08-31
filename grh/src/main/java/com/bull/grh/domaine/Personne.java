package com.bull.grh.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.bull.grh.domaine.types.Statut;

@Entity
public class Personne {

    @Id
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String fonction;
    private String email;
    @Enumerated
    private Statut statut;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<Authority>();

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public List<Authority> getAuthorities() {
	return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
	this.authorities = authorities;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public String getPrenom() {
	return prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    public String getFonction() {
	return fonction;
    }

    public void setFonction(String fonction) {
	this.fonction = fonction;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Statut getStatut() {
	return statut;
    }

    public void setStatut(Statut statut) {
	this.statut = statut;
    }

}