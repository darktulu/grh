package com.bull.grh.service.utils;

import com.bull.grh.view.admin.vo.EmailDTO;

public interface EmailService {
    void send(EmailDTO mail) throws Exception;
}
