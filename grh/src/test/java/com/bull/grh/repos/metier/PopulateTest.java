package com.bull.grh.repos.metier;

import java.util.Date;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.Demande;
import com.bull.grh.domaine.Entretien;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;
import com.bull.grh.view.metier.vo.EntretienVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml", "classpath:META-INF/spring/applicationContext.xml" })
//@TransactionConfiguration(defaultRollback = true)
//@Transactional
public class PopulateTest {

    @Inject
    private DemandeDao demandeDao;

    @Inject
    private DozerBeanMapper mapper;

    @Inject
    private CandidatureDao candidatureDao;

    @Inject
    private EntretienDao entretienDao;
    @Inject
    private CandidatDao candidatDao;

   // @Test
    public void test() {
	DemandeVO demande = new DemandeVO();
	demande.setIntitulePoste("intitulé test");
	demande.setDate(new Date());
	demande = mapper.map(demandeDao.save(mapper.map(demande, Demande.class)), DemandeVO.class);
	CandidatureVO candidature = new CandidatureVO();
	candidature.setDemande(demande);
	candidature.getCandidat().setUsername("user2");
	candidatureDao.save(mapper.map(candidature, Candidature.class));
    }

    @Test
    public void test_2() {
	EntretienVO entretien = new EntretienVO();
	entretien.setAdresse("une adress");
	entretien.setDate(new Date());
	entretien.setHeure("15h max");
	entretien.getCandidature().setId(1L);
	entretien.getPersonne().setUsername("testCE");
	Entretien entretienE = mapper.map(entretien ,Entretien.class);
	entretienE = entretienDao.save(entretienE);
	System.out.println(entretienE.getId());
	System.out.println(entretienE.getEvaluation().getId());
    }
}
