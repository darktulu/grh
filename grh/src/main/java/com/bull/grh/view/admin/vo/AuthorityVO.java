package com.bull.grh.view.admin.vo;

import java.io.Serializable;

public class AuthorityVO implements Serializable {

    private static final long serialVersionUID = 1L;
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
