package com.bull.grh.service.metier.impl;

import java.util.Date;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.repos.metier.CandidatureDao;
import com.bull.grh.service.metier.ConvocationService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DossierCandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml", "classpath:META-INF/spring/applicationContext.xml" })
public class ConvocationServiceImplTest {

    @Inject
    private ConvocationService convocationService;
    @Inject
    private CandidatureDao candidatureDao;
    @Inject
    private DozerBeanMapper mapper;
    
    private final String email = "kecha.mohamed@simu.com";
    private CandidatVO candidat = new CandidatVO();
    private DossierCandidatureVO dc = new DossierCandidatureVO();
    private CandidatureVO candidature = new CandidatureVO();
    private EntretienVO e = new EntretienVO();
    //private Map<String, Object> dataInput = new HashMap<String, Object>();

    @Before
    public void init() {
	candidat.setEmail(email);
	candidat.setNom("nom");
	candidat.setPrenom("prenom");
	candidat.setUsername("user2");
	candidat.setPassword("password");
	dc.setDossierComplet(false);
	candidature.setCandidat(candidat);
	candidature.setId(1L);
	candidature.getDemande().setId(1L);
	candidature.setDossierCandidature(dc);
	e.setAdresse("une adress");
	e.setDate(new Date());
	e.setHeure("15h max");
	e.setCandidature(candidature);
    }

    @Test
    public void test() {
	//candidatureDao.save(mapper.map(candidature, Candidature.class));
	//convocationService.completeCallingTask(e, candidature, new Task());
    }

}
