package com.bull.grh.view.metier;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.domaine.validator.Password;
import com.bull.grh.i18n.I18nMessageBean;
import com.bull.grh.process.exception.InvalidActivationTokenException;
import com.bull.grh.service.exception.CannotRegisterException;
import com.bull.grh.service.metier.CandidatService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.utils.FacesUtils;

@Component
@Scope("request")
public class RegisterBean {

	@Inject
	private CandidatService candidatService;

	@Inject
	private I18nMessageBean i18nMessageBean;

	@Value("${activationURI}")
	private String link;

	@NotBlank
	@Password
	private String rePassword;
	@Valid
	private CandidatVO newUser = new CandidatVO();

	public void register() {
		if (rePassword.equals(newUser.getPassword()))
			try {
				candidatService.register(newUser);
			} catch (CannotRegisterException e) {
				// TODO facesMessage
				e.printStackTrace();
			}
		else {
			// TODO facesMessage password don't match
		}
	}

	@PostConstruct
	public void validateAccount() {
		if (FacesUtils.getRequest().getRequestURI().equals(link)) {
			String token = FacesUtils.getRequest().getParameter("token");
			try {
				candidatService.activateAccount(token);
				i18nMessageBean.showInfoMessage("account.activated.info");
			} catch (InvalidActivationTokenException e) {
				// TODO No task to validate or time out
				i18nMessageBean.showErrorMessage("account.activated.exception");
			}
		}
	}

	public CandidatService getCandidatService() {
		return candidatService;
	}

	public void setCandidatService(CandidatService candidatService) {
		this.candidatService = candidatService;
	}

	public CandidatVO getNewUser() {
		return newUser;
	}

	public void setNewUser(CandidatVO newUser) {
		this.newUser = newUser;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public I18nMessageBean getI18nMessageBean() {
		return i18nMessageBean;
	}

	public void setI18nMessageBean(I18nMessageBean i18nMessageBean) {
		this.i18nMessageBean = i18nMessageBean;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
