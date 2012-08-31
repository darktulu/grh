package com.bull.grh.view.admin.vo;

import java.io.Serializable;

import com.bull.grh.domaine.types.EmailCode;

public class EmailTemplateVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private EmailCode code;
    private String subject;
    private String message;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public EmailCode getCode() {
	return code;
    }

    public void setCode(EmailCode code) {
	this.code = code;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
