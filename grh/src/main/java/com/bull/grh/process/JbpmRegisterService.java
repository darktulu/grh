package com.bull.grh.process;

import com.bull.grh.process.exception.InvalidActivationTokenException;
import com.bull.grh.service.exception.CannotRegisterException;
import com.bull.grh.view.metier.vo.CandidatVO;

public interface JbpmRegisterService {

    public void register(CandidatVO candidatVO) throws CannotRegisterException;

    public boolean activateAccount(String token) throws InvalidActivationTokenException;

}
