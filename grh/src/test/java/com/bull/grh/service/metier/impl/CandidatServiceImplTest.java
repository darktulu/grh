package com.bull.grh.service.metier.impl;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.process.exception.InvalidActivationTokenException;
import com.bull.grh.service.exception.CannotRegisterException;
import com.bull.grh.service.metier.CandidatService;
import com.bull.grh.view.metier.vo.CandidatVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml", "classpath:META-INF/spring/applicationContext.xml" })
public class CandidatServiceImplTest {

    @Inject
    private CandidatService candidatService;
    private final String email = "kecha.mohamed@simu.com";

    @Test
    public void test() throws CannotRegisterException, InvalidActivationTokenException {
	CandidatVO candidat = new CandidatVO();
	candidat.setEmail(email);
	candidat.setNom("nom");
	candidat.setPrenom("prenom");
	candidat.setUsername("user2");
	candidat.setPassword("password");

	candidatService.register(candidat);

//	try {
//	    Thread.currentThread();
//	    Thread.sleep(30000);
//	} catch (InterruptedException e) {
//	    e.printStackTrace();
//	}
//
//	CandidatVO candidat2 = candidat;
//	candidat2.setUsername("user2");
//
//	candidatService.register(candidat2);
//
//	try {
//	    Thread.currentThread();
//	    Thread.sleep(3000);
//	} catch (InterruptedException e) {
//	    e.printStackTrace();
//	}
//
//	candidatService.activateAccount(candidat2.getCodeActivationCompte());
//	
//	try {
//	    Thread.currentThread();
//	    Thread.sleep(10000);
//	} catch (InterruptedException e) {
//	    e.printStackTrace();
//	}
    }

}
