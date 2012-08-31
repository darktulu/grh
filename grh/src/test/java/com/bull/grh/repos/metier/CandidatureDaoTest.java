package com.bull.grh.repos.metier;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.Demande;
import com.bull.grh.domaine.DossierCandidature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml", "classpath:META-INF/spring/applicationContext.xml" })
public class CandidatureDaoTest {

    @Inject
    private CandidatureDao candidatureDao;

    @Test
    public void test() {
	Long count = candidatureDao.findByDemandeIdCount(4L);
	System.out.println("COMPTE : " + count);

	List<Candidature> x = candidatureDao.findByDemandeIdAndCandidatUsername(9L, "user2");
	assertTrue(x.size() == 1);

	Candidature c = new Candidature();
	c.setDemande(new Demande());
	c.setCandidat(new Candidat());
	c.setDossierCandidature(new DossierCandidature());
	c.getCandidat().setUsername("Aaron");
	c.getDemande().setId(11L);
	
	candidatureDao.save(c);
    }

}
