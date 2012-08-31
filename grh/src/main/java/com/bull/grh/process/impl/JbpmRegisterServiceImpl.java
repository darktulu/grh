package com.bull.grh.process.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.task.Task;
import org.springframework.stereotype.Service;

import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmRegisterService;
import com.bull.grh.process.exception.InvalidActivationTokenException;
import com.bull.grh.service.exception.CannotRegisterException;
import com.bull.grh.view.metier.vo.CandidatVO;

@Service("jbpmRegisterService")
public class JbpmRegisterServiceImpl extends JbpmServiceImpl implements JbpmRegisterService {

    @Override
    public void register(CandidatVO candidatVO) throws CannotRegisterException {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	dataInput.put(ProcessConst.REGISTER_PROCESS_CANDIDAT, candidatVO);
	startProces(ProcessConst.PROCESS_ID_INSCRIPTION, dataInput);
    }

    @Override
    public boolean activateAccount(String token) throws InvalidActivationTokenException {

	List<Task> tasks = getTasksAssigned(token);

	if (tasks == null || tasks.isEmpty())
	    throw new InvalidActivationTokenException();

	Task task = tasks.get(0);
	startAndCompleteTask(token, task);
	return true;
    }

}
