package com.bull.grh.process.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.task.Task;
import org.springframework.stereotype.Service;

import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmEntretienService;
import com.bull.grh.view.metier.vo.EntretienVO;

@Service("jbpmEntretienService")
public class JbpmEntretienServiceImpl extends JbpmServiceImpl implements JbpmEntretienService {

    @Override
    public void completeTask(String userID, Task task, EntretienVO entretien) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.APPOINTMENT_ENTRETIEN, entretien);

	completeTask(userID, task, dataInput);
    }

    @Override
    public void startProces(String userID, EntretienVO entretien) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.APPOINTMENT_ENTRETIEN, entretien);
	addDataInput(dataInput, ProcessConst.APPOINTMENT_USERNAME_CE, userID);

	startProces(ProcessConst.PROCESS_ID_ENTRETIEN, dataInput);
    }

    @Override
    public List<Task> getCETaskList(String role) {
	return getTaskList(ProcessConst.APPOINTMENT_TASK_CE, role);
    }

    @Override
    public Long getCountCETaskList(String role) {
	return getCountTaskList(ProcessConst.APPOINTMENT_TASK_CE, role);
    }

    @Override
    public List<Task> getCEStartedTaskList(String role) {
	return getStartedTaskList(ProcessConst.APPOINTMENT_TASK_CE, role);
    }

    @Override
    public Long getCountCEStartedTaskList(String role) {
	return getCountStartedTaskList(ProcessConst.APPOINTMENT_TASK_CE, role);
    }

    @Override
    public List<Task> getRHTaskList(String role) {
	return getTaskList(ProcessConst.APPOINTMENT_TASK_RH, role);
    }

    @Override
    public Long getCountRHTaskList(String role) {
	return getCountTaskList(ProcessConst.APPOINTMENT_TASK_RH, role);
    }

    @Override
    public List<Task> getRHStartedTaskList(String role) {
	return getStartedTaskList(ProcessConst.APPOINTMENT_TASK_RH, role);
    }

    @Override
    public Long getCountRHStartedTaskList(String role) {
	return getCountStartedTaskList(ProcessConst.APPOINTMENT_TASK_RH, role);
    }

    @Override
    public void startAndCompleteTask(String role, Task task, EntretienVO entretien) {
	Map<String, Object> dataInput = new HashMap<String, Object>();
	addDataInput(dataInput, ProcessConst.APPOINTMENT_ENTRETIEN, entretien);

	startAndCompleteTask(role, task, dataInput);
    }

}
