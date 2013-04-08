package com.bull.grh.view.utils;

import com.bull.grh.service.utils.AuthenticationService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.PersonneVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;

@Component
@Scope("session")
public class UserSessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public CandidatVO candidat;
    public PersonneVO personne;

    @Inject
    public AuthenticationService authenticationService;

    public CandidatVO getCandidat() {
        if (candidat == null)
            candidat = authenticationService.getConnectedCandidat();
        return candidat;
    }

    public void setCandidat(CandidatVO candidat) {
        this.candidat = candidat;
    }

    public PersonneVO getPersonne() {
        if (personne == null)
            personne = authenticationService.getConnectedPersonne();
        return personne;
    }

    public void setPersonne(PersonneVO personne) {
        this.personne = personne;
    }

}
