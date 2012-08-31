package com.bull.grh.process.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.task.Task;
import org.springframework.stereotype.Service;

import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmDemandeService;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;

@Service("jbpmDemandeService")
public class JbpmDemandeServiceImpl extends JbpmServiceImpl implements JbpmDemandeService {
    
    @Override
    public void startProcess(String userID, DemandeVO demande){
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.DEMANDE_DEMANDE, demande);

	startProces(ProcessConst.PROCESS_ID_DEMANDE_RECRUTEMENT, dataInput);
    }
    
    @Override
    public void startAndCompleteTask(String userID, Task task, DemandeVO demande) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.DEMANDE_DEMANDE, demande);

	startAndCompleteTask(userID, task, dataInput);
    }

    @Override
    public void startAndCompleteTask(String userID, Task task, DemandeVO demande, List<CandidatureVO> candidatureList) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.DEMANDE_DEMANDE, demande);
	addDataInput(dataInput, ProcessConst.DEMANDE_CANDIDATURE_LIST, candidatureList);

	startAndCompleteTask(userID, task, dataInput);
    }

    @Override
    public void completeTask(String userID, Task task, DemandeVO demande) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.DEMANDE_DEMANDE, demande);

	completeTask(userID, task, dataInput);
    }

    @Override
    public void completeTask(String userID, Task task, DemandeVO demande, List<CandidatureVO> candidatureList) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.DEMANDE_DEMANDE, demande);
	addDataInput(dataInput, ProcessConst.DEMANDE_CANDIDATURE_LIST, candidatureList);

	completeTask(userID, task, dataInput);
    }

}
