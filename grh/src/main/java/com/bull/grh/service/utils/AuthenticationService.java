package com.bull.grh.service.utils;

import org.springframework.security.core.AuthenticationException;

import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.PersonneVO;

public interface AuthenticationService {

    public void login(String login, String password) throws AuthenticationException;

    public void logout();
    
    public PersonneVO getConnectedPersonne();
    
    public CandidatVO getConnectedCandidat();
}
