package com.bull.grh.view.metier.vo;

import java.io.Serializable;

public class ActiviteExtraProfessionnelleVO implements Serializable {

	private Long id;
	private String type;
	private String activite;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getActivite() {
		return activite;
	}
	public void setActivite(String activite) {
		this.activite = activite;
	}
	
}
