package com.bull.grh.service.admin.impl;

import com.bull.grh.domaine.EmailTemplate;
import com.bull.grh.repos.admin.EmailTemplateDao;
import com.bull.grh.service.admin.AdminTemplateService;
import com.bull.grh.view.admin.vo.EmailTemplateVO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminTemplateServiceImpl implements AdminTemplateService {

    @Inject
    private EmailTemplateDao emailTemplateDao;

    @Inject
    private DozerBeanMapper mapper;

    @Override
    public EmailTemplateVO addorupdatEmailTemplate(@Valid EmailTemplateVO emailTemplate) {
        EmailTemplate templateEntity = mapper.map(emailTemplate, EmailTemplate.class);
        templateEntity = emailTemplateDao.save(templateEntity);
        return mapper.map(templateEntity, EmailTemplateVO.class);
    }

    @Override
    public void deleteEmailTemplate(EmailTemplateVO emailTemplate) {
        EmailTemplate templateEntity = mapper.map(emailTemplate, EmailTemplate.class);
        emailTemplateDao.delete(templateEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmailTemplateVO> loadEmailTemplates() {
        List<EmailTemplateVO> templates = new ArrayList<EmailTemplateVO>();
        List<EmailTemplate> templateEntityList = emailTemplateDao.findAll();
        if (templateEntityList == null || templateEntityList.isEmpty()) {
            // TODO nothing here
        } else {
            for (EmailTemplate e : templateEntityList)
                templates.add(mapper.map(e, EmailTemplateVO.class));
        }
        return templates;
    }
}
