package com.bull.grh.service.metier.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.service.metier.CvtequeService;
import com.bull.grh.view.metier.vo.CandidatVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml", "classpath:META-INF/spring/applicationContext.xml" })
public class CvtequeServiceImplTest {

    @Inject
    private CvtequeService cvtequeService;

    @Test
    public void test() {

	int i = 0;
	for (CandidatVO c : cvtequeService.loadAllCandidat(1, 20)) {
	    i = i + 1;
	    System.out.println(i + " " + c.getNom());
	}

	Map<String, String> map = new HashMap<String ,String>();
	map.put("nom", "b");
	map.put("prenom", "l");
	for (CandidatVO c : cvtequeService.loadCandidats(map)) {
	    System.out.println(c.getNom()+" "+c.getPrenom());
	}
    }

}
