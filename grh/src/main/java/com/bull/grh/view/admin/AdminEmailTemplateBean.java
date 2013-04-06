package com.bull.grh.view.admin;

import com.bull.grh.service.admin.AdminTemplateService;
import com.bull.grh.view.admin.vo.EmailTemplateVO;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Scope("view")
@Component
public class AdminEmailTemplateBean implements Serializable {

    private static final long serialVersionUID = 1L;
    protected static Logger logger = Logger.getLogger("AdminEmailTemplateBean");

    private List<EmailTemplateVO> templates;
    private EmailTemplateVO emailtemplate;

    @Inject
    private transient AdminTemplateService templateEmailService;

    public void updateEmailTemplate() {
        logger.debug("Update Email template");
        try {
            templateEmailService.addorupdatEmailTemplate(emailtemplate);
        } catch (Exception e) {
            logger.error("error updating Email Template");
        }
    }

    public void deleteEmailTemplate() {
        logger.debug("Delete Email template");
        try {
            templateEmailService.deleteEmailTemplate(emailtemplate);
        } catch (Exception e) {
            logger.error("error deleting Email Template");
        }
    }

    public void createEmailTemplate() {
        logger.debug("Create Email template");
        try {
            templateEmailService.addorupdatEmailTemplate(emailtemplate);
        } catch (Exception e) {
            logger.error("error creating Email Template");
        }
    }

    public List<EmailTemplateVO> getTemplates() {
        if (templates == null || templates.isEmpty())
            templates = templateEmailService.loadEmailTemplates();
        return templates;
    }

    public void setTemplates(List<EmailTemplateVO> templates) {
        this.templates = templates;
    }

    public EmailTemplateVO getEmailtemplate() {
        return emailtemplate;
    }

    public void setEmailtemplate(EmailTemplateVO emailtemplate) {
        this.emailtemplate = emailtemplate;
    }

    public AdminTemplateService getTemplateEmailService() {
        return templateEmailService;
    }

    public void setTemplateEmailService(AdminTemplateService templateEmailService) {
        this.templateEmailService = templateEmailService;
    }


}
