package com.bull.grh.process.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.jbpm.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.domaine.types.DecisionEntretien;
import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmEntretienService;
import com.bull.grh.process.JbpmService;
import com.bull.grh.repos.metier.EntretienDao;
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
public class JbpmEntretienServiceImplTest {

    @Autowired
    private JbpmEntretienService jbpmEntretienService;
    @Autowired
    private JbpmService jbpmService;
    @Autowired
    private EntretienDao entretienDao;
    @Autowired
    private DozerBeanMapper mapper;
    
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
	EntretienVO entretien = mapper.map(entretienDao.findOne(4L),EntretienVO.class);
	dataInput.put(ProcessConst.APPOINTMENT_ENTRETIEN, entretien);
	dataInput.put(ProcessConst.APPOINTMENT_USERNAME_CE , "ROLE_CE");
	jbpmService.startProces(ProcessConst.PROCESS_ID_ENTRETIEN, dataInput);
    }
    
    //@Test
    public void getCount(){
	Long x = jbpmEntretienService.getCountCETaskList("ROLE_CE");
	System.out.println("COUNT TASKS : "+x);
    }
    
    @Test
    public void getTasks(){
	//List<Task> x = jbpmConvocationService.getTaskList("ROLE_RH");
//	System.out.println("TASKS LIST : "+x);
    }
    
  //  @Test
    public void completeTask() throws InterruptedException{
	List<Task> x = jbpmEntretienService.getRHTaskList("ROLE_RH");
	EntretienVO e = new EntretienVO();
	Task t = x.get(0);
	    System.out.println("TASK ID"+t.getId());
	    e = (EntretienVO) jbpmEntretienService.getTaskContent(t).get(ProcessConst.APPOINTMENT_ENTRETIEN);
	    jbpmEntretienService.startTask("ROLE_RH", t);
	    e.setDecisionEntretien(DecisionEntretien.OK);
	    jbpmEntretienService.completeTask("ROLE_RH", t, e);
	System.out.println("COMPLETE !!");
	Thread.sleep(9000);
    }

}
