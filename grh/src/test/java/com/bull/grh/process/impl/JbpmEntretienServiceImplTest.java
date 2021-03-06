package com.bull.grh.process.impl;

import com.bull.grh.repos.metier.EntretienDao;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DossierCandidatureVO;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/applicationContext-data.xml",
        "classpath:META-INF/spring/applicationContext-mail.xml",
        "classpath:META-INF/spring/applicationContext-security.xml",
        "classpath:META-INF/spring/applicationContext-aop.xml",
        "classpath:META-INF/spring/applicationContext.xml"})
public class JbpmEntretienServiceImplTest {

    @Inject
    private EntretienDao entretienDao;
    @Inject
    private DozerBeanMapper mapper;

    private final String email = "kecha.mohamed@simu.com";
    private CandidatVO candidat = new CandidatVO();
    private DossierCandidatureVO dc = new DossierCandidatureVO();
    private CandidatureVO candidature = new CandidatureVO();
    private Map<String, Object> dataInput = new HashMap<String, Object>();

    @Before
    public void init() {
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
//
//   @Test
//    public void test() {
//	EntretienVO entretien = mapper.map(entretienDao.findOne(4L),EntretienVO.class);
//	dataInput.put(ProcessConst.APPOINTMENT_ENTRETIEN, entretien);
//	dataInput.put(ProcessConst.APPOINTMENT_USERNAME_CE , "ROLE_CE");
//	jbpmService.startProces(ProcessConst.PROCESS_ID_ENTRETIEN, dataInput);
//    }
//
//    //@Test
//    public void getCount(){
//	Long x = jbpmEntretienService.getCountCETaskList("ROLE_CE");
//	System.out.println("COUNT TASKS : "+x);
//    }
//
//    @Test
//    public void getTasks(){
//	//List<Task> x = jbpmConvocationService.getTaskList("ROLE_RH");
////	System.out.println("TASKS LIST : "+x);
//    }
//
//  //  @Test
//    public void completeTask() throws InterruptedException{
//	List<Task> x = jbpmEntretienService.getRHTaskList("ROLE_RH");
//	EntretienVO e = new EntretienVO();
//	Task t = x.get(0);
//	    System.out.println("TASK ID"+t.getId());
//	    e = (EntretienVO) jbpmEntretienService.getTaskContent(t).get(ProcessConst.APPOINTMENT_ENTRETIEN);
//	    jbpmEntretienService.startTask("ROLE_RH", t);
//	    e.setDecisionEntretien(DecisionEntretien.OK);
//	    jbpmEntretienService.completeTask("ROLE_RH", t, e);
//	System.out.println("COMPLETE !!");
//	Thread.sleep(9000);
//    }

}
