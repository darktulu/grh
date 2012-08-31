package com.bull.grh.service.admin;

import java.util.List;

import com.bull.grh.view.admin.vo.EmailTemplateVO;

public interface AdminTemplateService {

    public EmailTemplateVO addorupdatEmailTemplate(EmailTemplateVO emailTemplate);

    public void deleteEmailTemplate(EmailTemplateVO emailTemplate);

    public List<EmailTemplateVO> loadEmailTemplates();

}
