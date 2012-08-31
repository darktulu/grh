package com.bull.grh.view.admin.vo;

import java.io.Serializable;

import com.bull.grh.domaine.types.EmailCode;

public class EmailVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private EmailCode code;
    private String to;
    private String from;
    private String subject;
    private String body;
    private String replyTo;
    private String Cc;
    private String Bcc;

    public String getCc() {
	return Cc;
    }

    public void setCc(String cc) {
	Cc = cc;
    }

    public String getBcc() {
	return Bcc;
    }

    public void setBcc(String bcc) {
	Bcc = bcc;
    }

    public void setReplyTo(String replyTo) {
	this.replyTo = replyTo;
    }

    public String getReplyTo() {
	return replyTo;
    }

    public EmailCode getCode() {
	return code;
    }

    public void setCode(EmailCode code) {
	this.code = code;
    }

    public String getTo() {
	return to;
    }

    public void setTo(String to) {
	this.to = to;
    }

    public String getFrom() {
	return from;
    }

    public void setFrom(String from) {
	this.from = from;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

}