package com.bull.grh.service.utils.impl;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bull.grh.repos.admin.PersonneDao;
import com.bull.grh.repos.metier.CandidatDao;
import com.bull.grh.service.utils.AuthenticationService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.PersonneVO;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	@Inject
	private AuthenticationManager authenticationManager;
	@Inject
	private PersonneDao personneDao;
	@Inject
	private CandidatDao candidatDao;
	@Inject
	private DozerBeanMapper mapper;

	@Override
	public void login(String login, String password) throws AuthenticationException {
		Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password));
		if (authenticate.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticate);
		}
	}

	@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Override
	public PersonneVO getConnectedPersonne() {
		return mapper.map(personneDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()),
				PersonneVO.class);
	}

	@Override
	public CandidatVO getConnectedCandidat() {
		return mapper.map(candidatDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()),
				CandidatVO.class);
	}

}
