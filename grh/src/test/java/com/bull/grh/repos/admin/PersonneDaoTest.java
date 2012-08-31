package com.bull.grh.repos.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bull.grh.domaine.Personne;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml",
	"classpath:META-INF/spring/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class PersonneDaoTest {

    @Autowired
    private PersonneDao personneDao;

    @Test
    public void test() {
	Personne personne = new Personne();
	personne.setNom("Kecha");
	personne.setPrenom("mohamed");
	personne = personneDao.save(personne);

	System.out.println(personne.getUsername());
    }

}
