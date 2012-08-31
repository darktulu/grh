package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.bull.grh.domaine.types.Statut;
import com.bull.grh.domaine.validator.Email;
import com.bull.grh.view.admin.vo.AuthorityVO;

public class PersonneVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    private String username;
    @NotNull
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String fonction;
    @Email
    private String email;
    private Statut statut;

    private List<AuthorityVO> authorities = new ArrayList<AuthorityVO>();

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
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

    public List<AuthorityVO> getAuthorities() {
	return authorities;
    }

    public void setAuthorities(List<AuthorityVO> authorities) {
	this.authorities = authorities;
    }

    @Override
    public String toString() {
	return "PersonneVO [username=" + username + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
		+ prenom + ", fonction=" + fonction + ", email=" + email + "]";
    }

}
