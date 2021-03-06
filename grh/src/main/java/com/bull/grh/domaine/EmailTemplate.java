package com.bull.grh.domaine;

import com.bull.grh.domaine.types.EmailType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class EmailTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @Enumerated
    private EmailType code;
    @Column(length = 1000)
    private String message;
    private String subject;
    private Date created;
    private Date updated;
    private String definition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmailType getCode() {
        return code;
    }

    public void setCode(EmailType code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}