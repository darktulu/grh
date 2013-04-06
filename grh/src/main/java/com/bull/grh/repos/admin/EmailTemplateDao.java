package com.bull.grh.repos.admin;

import com.bull.grh.domaine.EmailTemplate;
import com.bull.grh.domaine.types.EmailCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTemplateDao extends JpaRepository<EmailTemplate, Long> {
    EmailTemplate findByCode(EmailCode code);
}
