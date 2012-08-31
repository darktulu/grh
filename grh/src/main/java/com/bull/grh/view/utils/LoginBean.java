package com.bull.grh.view.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bull.grh.service.utils.AuthenticationService;

@Component
@Scope("request")
public class LoginBean {

    @NotNull
    private String username;
    @NotNull
    private String password;

    @Inject
    private AuthenticationService authenticationService;

    public String doLogin() {
	try {
	    authenticationService.login(username, password);
	    GrantedAuthority auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator()
		    .next();
	    if ("ROLE_OP".equals(auth.getAuthority())) {
		return "modeOP/index.xhtml?faces-redirect=true";
	    } else if ("ROLE_RH".equals(auth.getAuthority())) {
		return "modeRH/index.xhtml?faces-redirect=true";
	    } else if ("ROLE_CE".equals(auth.getAuthority())) {
		return "modeCE/index.xhtml?faces-redirect=true";
	    } else
		return "candidat/profil.xhtml?faces-redirect=true";

	} catch (AuthenticationException e) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("login or password incorrect"));
	    return "login";
	}
    }

    public String doLogout() {
	authenticationService.logout();
	return "login";
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

    public AuthenticationService getAuthenticationService() {
	return authenticationService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
	this.authenticationService = authenticationService;
    }

}
