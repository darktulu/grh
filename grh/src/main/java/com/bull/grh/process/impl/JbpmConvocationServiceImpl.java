package com.bull.grh.process.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.task.Task;
import org.springframework.stereotype.Service;

import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmConvocationService;
import com.bull.grh.view.metier.vo.EntretienVO;

@Service("jbpmConvocationService")
public class JbpmConvocationServiceImpl extends JbpmServiceImpl implements JbpmConvocationService {

    @Override
    public void completeTask(String userID, Task task, EntretienVO entretien, boolean disponible) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.CONVOCATION_DISPONIBLE, disponible);
	addDataInput(dataInput, ProcessConst.CONVOCATION_ENTRETIEN, entretien);

	completeTask(userID, task, dataInput);
    }

    @Override
    public void startAndCompleteTask(String userID, Task task, EntretienVO entretien, boolean disponible) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.CONVOCATION_DISPONIBLE, disponible);
	addDataInput(dataInput, ProcessConst.CONVOCATION_ENTRETIEN, entretien);

	startAndCompleteTask(userID, task, dataInput);
    }
    
    @Override
    public List<Task> getTaskList(String role) {
	return getTaskList(ProcessConst.CONVOCATION_TASK_CONVOCATION, role);
    }

    @Override
    public Long getCountTaskList(String role) {
	return getCountTaskList(ProcessConst.CONVOCATION_TASK_CONVOCATION, role);
    }
    
    @Override
    public List<Task> getStartedTaskList(String role) {
	return getStartedTaskList(ProcessConst.CONVOCATION_TASK_CONVOCATION, role);
    }

    @Override
    public Long getCountStartedTaskList(String role) {
	return getCountStartedTaskList(ProcessConst.CONVOCATION_TASK_CONVOCATION, role);
    }

}
