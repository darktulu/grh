package com.bull.grh.process.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.jbpm.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bull.grh.domaine.types.EtatDemande;
import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmDemandeService;
import com.bull.grh.repos.metier.DemandeDao;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/applicationContext-data.xml",
	"classpath:META-INF/spring/applicationContext-mail.xml",
	"classpath:META-INF/spring/applicationContext-jbpm.xml",
	"classpath:META-INF/spring/applicationContext-security.xml",
	"classpath:META-INF/spring/applicationContext-aop.xml",
	"classpath:META-INF/spring/applicationContext.xml" })
public class JbpmDemandeServiceImplTest {

    @Inject
    private JbpmDemandeService jbpmDemandeService;
    
    @Inject
    private DozerBeanMapper mapper;
    
    @Inject
    private DemandeDao demandeDao;
    
    
//    @Test
    public void test() {
	DemandeVO demande = new DemandeVO();
//	demande.setDate(new Date());
//	demande.setIntitulePoste("Intitulé test de demande 2");
//	demande.setNbPersonnes(10);
//	demande.getPersonne().setUsername("testOP");
//	demande = mapper.map(demandeDao.save(mapper.map(demande, Demande.class)),DemandeVO.class);
	demande = mapper.map(demandeDao.findOne(5L), DemandeVO.class);
	jbpmDemandeService.startProcess(null, demande);
	demande = mapper.map(demandeDao.findOne(6L), DemandeVO.class);
	jbpmDemandeService.startProcess(null, demande);
    }
    
    @Test
    public void test_1(){
	Long count = jbpmDemandeService.getCountTaskList(ProcessConst.DEMANDE_TASK_OP_CHOICE, "ROLE_OP");
	System.out.println("TASK COUNT: "+count);
    }

    @Test
    public void test_2() throws InterruptedException{
	List<Task> tasks = jbpmDemandeService.getTaskList(ProcessConst.DEMANDE_TASK_OP_CHOICE, "ROLE_OP");
	System.out.println(tasks);
	Task t = tasks.get(0);
	DemandeVO d = (DemandeVO) jbpmDemandeService.getTaskContent(t).get(ProcessConst.DEMANDE_DEMANDE);
	List<CandidatureVO> l = new ArrayList<CandidatureVO>();
	for(int i = 0; i<2 ;i++) {
	    CandidatureVO c = new CandidatureVO();
	    c.setDemande(d);
	    c.getCandidat().setUsername("user2");
	    l.add(c);
	}
	d.setEtatDemande(EtatDemande.COMPLETED);
	jbpmDemandeService.startAndCompleteTask("ROLE_OP", t, d,l);
	
//	t = tasks.get(1);
//	d = (DemandeVO) jbpmDemandeService.getTaskContent(t).get(ProcessConst.DEMANDE_DEMANDE);
//	d.setEtatDemande(EtatDemande.SENT);
//	jbpmDemandeService.startAndCompleteTask("ROLE_OP", t, d);
	
	Thread.sleep(9000);
    }
}
