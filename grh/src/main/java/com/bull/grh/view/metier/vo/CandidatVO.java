package com.bull.grh.view.metier.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.bull.grh.domaine.types.Gender;
import com.bull.grh.domaine.validator.Email;
import com.bull.grh.domaine.validator.Password;
import com.bull.grh.domaine.validator.Phone;
import com.bull.grh.view.admin.vo.ValueVO;

public class CandidatVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Length(max = 35, min = 2)
	private String nom;
	@Length(max = 35, min = 2)
	private String prenom;
	private Gender sexe;
	private int age;
	@Length(max = 450, min = 2)
	private String adresse;
	@Email
	@NotBlank
	private String email;
	@Length(max = 20, min = 2)
	@NotBlank
	private String username;
	@Password
	private String password;
	@Phone
	private String telephone;
	private String reponseQuestionSecrete;
	private String cv;
	private String photo;
	private String commentaireRH;
	private String codeActivationCompte;
	private boolean showDossierCandidature;
	private boolean compteActive;
	private ValueVO situationFamiliale;
	private ValueVO ville;
	private ValueVO pays;
	private ValueVO nationalite;
	private ValueVO questionSecrete;

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

	public ValueVO getSituationFamiliale() {
		return situationFamiliale;
	}

	public void setSituationFamiliale(ValueVO situationFamiliale) {
		this.situationFamiliale = situationFamiliale;
	}

	public ValueVO getVille() {
		return ville;
	}

	public void setVille(ValueVO ville) {
		this.ville = ville;
	}

	public ValueVO getPays() {
		return pays;
	}

	public void setPays(ValueVO pays) {
		this.pays = pays;
	}

	public ValueVO getNationalite() {
		return nationalite;
	}

	public void setNationalite(ValueVO nationalite) {
		this.nationalite = nationalite;
	}

	public ValueVO getQuestionSecrete() {
		return questionSecrete;
	}

	public void setQuestionSecrete(ValueVO questionSecrete) {
		this.questionSecrete = questionSecrete;
	}

	@Override
	public String toString() {
		return "CandidatVO [" + (nom != null ? "nom=" + nom + ", " : "")
				+ (prenom != null ? "prenom=" + prenom + ", " : "") + (email != null ? "email=" + email + ", " : "")
				+ (username != null ? "username=" + username + ", " : "") + "showDossierCandidature="
				+ showDossierCandidature + ", compteActive=" + compteActive + "]";
	}

}
