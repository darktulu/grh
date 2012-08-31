package com.bull.grh.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class WebPage {

    @Id
    @GeneratedValue
    private Long id;
    private String url;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<Authority>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public List<Authority> getAuthorities() {
	return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
	this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
	if (!getAuthorities().contains(authority))
	    authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
	if (getAuthorities().contains(authority))
	    authorities.remove(authority);
    }
}
