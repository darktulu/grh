package com.bull.grh.i18n;

import java.util.Locale;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-data.xml",
		"classpath:META-INF/spring/applicationContext-mail.xml",
		"classpath:META-INF/spring/applicationContext-jbpm.xml",
		"classpath:META-INF/spring/applicationContext-security.xml",
		"classpath:META-INF/spring/applicationContext-aop.xml", "classpath:META-INF/spring/applicationContext.xml" })
public class I18nMessageBeanTest {

	@Inject
	private I18nMessageBean messageBean;

	@Test
	public void test() {
		MessageBuilder builder = new MessageBuilder().code("account.activated.info");
		String test = builder.build().resolveMessage(I18nMessageBean.getMessageSource(), Locale.FRANCE).getText();
		System.out.println(test);
	}
}
