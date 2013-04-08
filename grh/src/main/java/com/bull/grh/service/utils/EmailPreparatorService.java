package com.bull.grh.service.utils;

import com.bull.grh.domaine.types.EmailType;
import com.bull.grh.view.admin.vo.EmailDTO;

import java.util.Map;

public interface EmailPreparatorService {
    EmailDTO populateEmail(EmailType code, String to, String from, Map<String, Object> params, String cc,
                           String bcc) throws Exception;

    void prepare(EmailDTO mail) throws Exception;
}
