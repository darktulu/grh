package com.bull.grh.repos.metier;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.domaine.types.EtatEntretien;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml", "classpath:META-INF/spring/applicationContext.xml" })
public class EntretienDaoTest {

    @Inject
    private EntretienDao entretienDao;
    
    @Test
    public void test() {
	System.out.println(entretienDao.getEntretiensCount("testCE", EtatEntretien.COMPLETED));
	System.out.println(entretienDao.findByPersonneUsernameAndEtat("testCE", EtatEntretien.NEW));
    }

}
