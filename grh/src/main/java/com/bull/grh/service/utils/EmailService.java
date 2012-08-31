package com.bull.grh.service.utils;

import java.util.Map;

import com.bull.grh.domaine.types.EmailCode;
import com.bull.grh.view.admin.vo.EmailVO;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.EntretienVO;

public interface EmailService {

    public EmailVO populateEmail(EmailCode code, String to, String from, Map<String, String> params, String cc,
	    String bcc) throws Exception;

    // TODO comment
    public EmailVO emailAccountActivated(CandidatVO candidatVO);

    // TODO comment
    public EmailVO emailToActivateAccount(CandidatVO candidatVO);

    // TODO comment
    public EmailVO emailReActivate(CandidatVO candidatVO);

    // TODO comment
    public EmailVO emailConvocation(EntretienVO entretien);
    
 // TODO comment
    public EmailVO emailEntretien(EntretienVO entretien);
}
