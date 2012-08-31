package com.bull.grh.repos.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bull.grh.domaine.EmailTemplate;
import com.bull.grh.domaine.types.EmailCode;

@Repository("emailTemplateDao")
public interface EmailTemplateDao extends JpaRepository<EmailTemplate, Long>{

    public EmailTemplate findByCode(EmailCode code);
}
