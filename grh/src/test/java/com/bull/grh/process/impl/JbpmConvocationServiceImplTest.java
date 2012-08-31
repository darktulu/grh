package com.bull.grh.process.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmConvocationService;
import com.bull.grh.process.JbpmService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DossierCandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml",
	"classpath:META-INF/spring/applicationContext.xml" })
public class JbpmConvocationServiceImplTest {

    @Autowired
    private JbpmConvocationService jbpmConvocationService;
    @Autowired
    private JbpmService jbpmService;
    
    private final String email = "kecha.mohamed@simu.com";
    private CandidatVO candidat = new CandidatVO();
    private DossierCandidatureVO dc = new DossierCandidatureVO();
    private CandidatureVO candidature = new CandidatureVO();
    private Map<String, Object> dataInput = new HashMap<String, Object>();
    
    @Before
    public void init(){
	candidat.setEmail(email);
	candidat.setNom("nom");
	candidat.setPrenom("prenom");
	candidat.setUsername("user2");
	candidat.setPassword("password");
	dc.setDossierComplet(false);
	candidature.setId(1L);
	candidature.setCandidat(candidat);
	candidature.setDossierCandidature(dc);
    }
    
    @Test
    public void test() {
	dataInput.put(ProcessConst.CONVOCATION_CANDIDATURE, candidature);
	jbpmService.startProces(ProcessConst.PROCESS_ID_CONVOCATION, dataInput);
	jbpmService.startProces(ProcessConst.PROCESS_ID_CONVOCATION, dataInput);
    }
    
    @Test
    public void getCount(){
	Long x = jbpmConvocationService.getCountTaskList("ROLE_RH");
	System.out.println("COUNT TASKS : "+x);
    }
    
    @Test
    public void getTasks(){
	List<Task> x = jbpmConvocationService.getTaskList("ROLE_RH");
	System.out.println("TASKS LIST : "+x);
    }
    
    //@Test
    public void completeTask() throws InterruptedException{
	List<Task> x = jbpmConvocationService.getTaskList("ROLE_RH");
	EntretienVO e = new EntretienVO();
	e.setAdresse("une adress");
	e.setDate(new Date());
	e.setHeure("15h max");
	e.setCandidature(candidature);
	for(Task t : x){
	    System.out.println("TASK ID"+t.getId());
	    e.setCandidature((CandidatureVO) jbpmConvocationService.getTaskContent(t).get(ProcessConst.CONVOCATION_CANDIDATURE));
	    jbpmConvocationService.startAndCompleteTask("ROLE_RH",t, e, true);
	}
	System.out.println("COMPLETE !!");
	Thread.sleep(9000);
    }

}
