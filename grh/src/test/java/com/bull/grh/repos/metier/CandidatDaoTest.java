package com.bull.grh.repos.metier;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.view.metier.vo.CandidatVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml", "classpath:META-INF/spring/applicationContext.xml" })
// @TransactionConfiguration(defaultRollback = true)
// @Transactional
public class CandidatDaoTest {

    @Autowired
    private CandidatDao candidatDao;
    @Inject
    private DozerBeanMapper mapper;
    private final String email = "kecha.mohamed@simu.com";

    @Test
    public void test() {
	CandidatVO candidat = new CandidatVO();
	candidat.setEmail(email);
	candidat.setNom("Kecha");
	candidat.setPrenom("Mohamed");
	candidat.setUsername("user2");
	candidat.setPassword("password");

	candidatDao.save(mapper.map(candidat, Candidat.class));
	System.out.println(candidatDao.findOne("user2"));

    }

}
