package com.bull.grh.repos.admin;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bull.grh.domaine.EmailTemplate;
import com.bull.grh.domaine.types.EmailCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml",
	"classpath:META-INF/spring/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class EmailTemplateDaoTest {

    @Inject
    private EmailTemplateDao emailTemplateDao;
    
//    @Test
//    public void test() {
//	EmailTemplate email = emailTemplateDao.findByCode(EmailCode.REGISTER_NEW_ACCOUNT);
//	System.out.println(email.getMessage());
//    }

}
