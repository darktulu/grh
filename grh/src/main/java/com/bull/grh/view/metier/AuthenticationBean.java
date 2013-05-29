package com.bull.grh.view.metier;

import com.bull.grh.service.exception.CandidatNotFoundException;
import com.bull.grh.service.metier.CandidatService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.google.common.base.Strings;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;

@Component
@Scope("session")
public class AuthenticationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CandidatService candidatService;

    private String username;
    private CandidatVO candidat;

    public String getUsername() {
        if (Strings.isNullOrEmpty(username)) {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CandidatVO getCandidat() {
        if (Strings.isNullOrEmpty(username)){
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        if (candidat == null)
            try {
                candidat = candidatService.findCandidat(username);
            } catch (CandidatNotFoundException e) { // TODO message
            }
        return candidat;
    }

    public void setCandidat(CandidatVO candidat) {
        this.candidat = candidat;
    }
}
