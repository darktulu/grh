package com.bull.grh.view.metier;

import com.bull.grh.domaine.validator.FieldMatch;
import com.bull.grh.domaine.validator.Password;
import com.bull.grh.service.exception.CannotRegisterException;
import com.bull.grh.service.exception.InvalidActivationTokenException;
import com.bull.grh.service.metier.CandidatService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.utils.FacesUtils;
import com.bull.grh.view.utils.MessagesBean;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.Valid;

import static com.bull.grh.view.utils.MessagesBean.MessageType;

@Component
@Scope("request")
public class RegisterBean {
    @Inject
    private CandidatService candidatService;
    @Inject
    private MessagesBean messagesBean;

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
                e.printStackTrace();
                messagesBean.showMessage(MessageType.DEFAULT, "general.failure.title", "general.failure");
            }
        else {
            // TODO facesMessage password don't match
            messagesBean.showMessage(MessageType.DEFAULT, "general.failure.title", "account.activated.exception");
        }
    }

    @PostConstruct
    public void validateAccount() {
        if (FacesUtils.getRequest().getRequestURI().equals(link)) {
            String token = FacesUtils.getRequest().getParameter("token");
            try {
                candidatService.activateAccount(token);
                messagesBean.showMessage(MessageType.SUCCESS, "general.success.title", "account.activated.info");
            } catch (InvalidActivationTokenException e) {
                messagesBean.showMessage(MessageType.ERROR, "general.failure.title", "account.activated.exception");
            }
        }
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
