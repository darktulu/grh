package com.bull.grh.domaine;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.bull.grh.domaine.types.EmailCode;

@Entity
public class EmailTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @Enumerated
    private EmailCode code;
    private String subject;
    @Column(length = 1000)
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