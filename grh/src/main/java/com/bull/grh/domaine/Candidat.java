package com.bull.grh.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.bull.grh.domaine.types.Gender;

@Entity
public class Candidat {

    private String nom;
    private String prenom;
    @Enumerated
    private Gender sexe;
    private int age;
    private String adresse;
    private String email;
    @Id
    private String username;
    private String password;
    private String telephone;
    private String reponseQuestionSecrete;
    private String cv;
    private String photo;
    private String commentaireRH;
    private String codeActivationCompte;
    private boolean showDossierCandidature;
    private boolean compteActive;

    @ManyToOne
    private ValueList situationFamiliale;
    @ManyToOne
    private ValueList ville;
    @ManyToOne
    private ValueList pays;
    @ManyToOne
    private ValueList nationalite;
    @ManyToOne
    private ValueList questionSecrete;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<Authority>();

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

    public Gender getSexe() {
	return sexe;
    }

    public void setSexe(Gender sexe) {
	this.sexe = sexe;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public String getAdresse() {
	return adresse;
    }

    public void setAdresse(String adresse) {
	this.adresse = adresse;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getTelephone() {
	return telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    public String getReponseQuestionSecrete() {
	return reponseQuestionSecrete;
    }

    public void setReponseQuestionSecrete(String reponseQuestionSecrete) {
	this.reponseQuestionSecrete = reponseQuestionSecrete;
    }

    public String getCv() {
	return cv;
    }

    public void setCv(String cv) {
	this.cv = cv;
    }

    public String getPhoto() {
	return photo;
    }

    public void setPhoto(String photo) {
	this.photo = photo;
    }

    public String getCommentaireRH() {
	return commentaireRH;
    }

    public void setCommentaireRH(String commentaireRH) {
	this.commentaireRH = commentaireRH;
    }

    public String getCodeActivationCompte() {
	return codeActivationCompte;
    }

    public void setCodeActivationCompte(String codeActivationCompte) {
	this.codeActivationCompte = codeActivationCompte;
    }

    public boolean isShowDossierCandidature() {
	return showDossierCandidature;
    }

    public void setShowDossierCandidature(boolean showDossierCandidature) {
	this.showDossierCandidature = showDossierCandidature;
    }

    public boolean isCompteActive() {
	return compteActive;
    }

    public void setCompteActive(boolean compteActive) {
	this.compteActive = compteActive;
    }

    public ValueList getSituationFamiliale() {
	return situationFamiliale;
    }

    public void setSituationFamiliale(ValueList situationFamiliale) {
	this.situationFamiliale = situationFamiliale;
    }

    public ValueList getVille() {
	return ville;
    }

    public void setVille(ValueList ville) {
	this.ville = ville;
    }

    public ValueList getPays() {
	return pays;
    }

    public void setPays(ValueList pays) {
	this.pays = pays;
    }

    public ValueList getNationalite() {
	return nationalite;
    }

    public void setNationalite(ValueList nationalite) {
	this.nationalite = nationalite;
    }

    public ValueList getQuestionSecrete() {
	return questionSecrete;
    }

    public void setQuestionSecrete(ValueList questionSecrete) {
	this.questionSecrete = questionSecrete;
    }

    public List<Authority> getAuthorities() {
	return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
	this.authorities = authorities;
    }

}
