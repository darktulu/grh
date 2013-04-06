package com.bull.grh.service.admin;

import com.bull.grh.view.admin.vo.EmailTemplateVO;

import java.util.List;

public interface AdminTemplateService {
    EmailTemplateVO addorupdatEmailTemplate(EmailTemplateVO emailTemplate);

    void deleteEmailTemplate(EmailTemplateVO emailTemplate);

    List<EmailTemplateVO> loadEmailTemplates();
}
