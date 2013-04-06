package com.bull.grh.i18n;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Locale;

@Component("i18nMessageBean")
public class I18nMessageBean implements MessageSourceAware {

    private static MessageSource messageSource;

    public void showErrorMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        facesMessage.setDetail(new MessageBuilder().code(message).build()
                .resolveMessage(messageSource, Locale.FRANCE).getText());
        facesContext.addMessage(null, facesMessage);
    }

    public void showErrorMessage(String message, String[] params) {
        MessageBuilder builder = new MessageBuilder().code(message);
        if (params != null)
            builder = builder.args(params);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        facesMessage.setDetail(builder.build()
                .resolveMessage(messageSource, Locale.FRANCE).getText());
        facesContext.addMessage(null, facesMessage);
    }

    public void showWarningMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(message);
        facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        facesMessage.setDetail(new MessageBuilder().code(message).build()
                .resolveMessage(messageSource, Locale.FRANCE).getText());
        facesContext.addMessage(null, facesMessage);
    }

    public void showInfoMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        facesMessage.setDetail(new MessageBuilder().code(message).build()
                .resolveMessage(messageSource, Locale.FRANCE).getText());
        facesContext.addMessage(null, facesMessage);
    }

    public void showInfoMessage(String message, String[] params) {
        MessageBuilder builder = new MessageBuilder().code(message);
        if (params != null)
            builder = builder.args(params);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        facesMessage.setDetail(builder.build()
                .resolveMessage(messageSource, Locale.FRANCE).getText());
        facesContext.addMessage(null, facesMessage);
    }

    public void setMessageSource(MessageSource msgsource) {
        messageSource = msgsource;
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }
}
