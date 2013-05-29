package com.bull.grh.view.metier;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bull.grh.i18n.I18nMessageBean;
import com.bull.grh.service.exception.CandidatNotFoundException;
import com.bull.grh.service.metier.CandidatService;
import com.bull.grh.view.metier.vo.CandidatVO;

@Component
@Scope("request")
public class CandidatProfilBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private CandidatService candidatService;
    @Inject
    private I18nMessageBean i18nMessageBean;
    
    private CandidatVO candidat;

    public CandidatService getCandidatService() {
        return candidatService;
    }

    public void setCandidatService(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    public I18nMessageBean getI18nMessageBean() {
        return i18nMessageBean;
    }

    public void setI18nMessageBean(I18nMessageBean i18nMessageBean) {
        this.i18nMessageBean = i18nMessageBean;
    }

    public CandidatVO getCandidat() {
	try {
	    candidat = candidatService.findCandidat(SecurityContextHolder.getContext().getAuthentication().getName());
	} catch (CandidatNotFoundException e) {
	    // TODO CandidatNotFound à implementer
	    e.printStackTrace();
	}
	return candidat;
    }

    public void setCandidat(CandidatVO candidat) {
        this.candidat = candidat;
    }

}
