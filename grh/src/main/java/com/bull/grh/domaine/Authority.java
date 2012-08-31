package com.bull.grh.domaine;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Authority {

    @Id
    private String role;
    private Integer position;
    private Integer level;

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public Integer getPosition() {
	return position;
    }

    public void setPosition(Integer position) {
	this.position = position;
    }

    public Integer getLevel() {
	return level;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }
}
