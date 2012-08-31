package com.bull.grh.process.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.task.AccessType;
import org.jbpm.task.Content;
import org.jbpm.task.Task;
import org.jbpm.task.TaskData;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.ContentData;
import org.jbpm.task.service.TaskClient;
import org.jbpm.task.service.responsehandlers.BlockingGetContentResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingGetTaskResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingQueryGenericResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskOperationResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskSummaryResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmService;
import com.bull.grh.repos.metier.CandidatDao;
import com.bull.grh.repos.metier.EntretienDao;
import com.bull.grh.service.utils.EmailService;

/**
 * 
 * @author Mohamed.Kecha
 * 
 */
@Service("jbpmService")
public class JbpmServiceImpl implements JbpmService {

    @Autowired
    private StatefulKnowledgeSession ksession;

    @Autowired
    private TaskClient taskClient;

    @Autowired
    private CandidatDao candidatDao;

    @Autowired
    private EntretienDao entretienDao;

    @Autowired
    private EmailService emailService;

    protected static Logger logger = Logger.getLogger("JbpmService");

    @PostConstruct
    private void init() {
	ksession.setGlobal(ProcessConst.PROCESS_GLOBAL_MAIL_SERVICE, emailService);
	ksession.setGlobal(ProcessConst.PROCESS_GLOBAL_DAO_CANDIDAT, candidatDao);
    }

    @Override
    public void startProces(String processID) {
	if (processID == null || "".equals(processID)) {
	    logger.error("processID is null");
	} else {
	    ksession.startProcess(processID);
	}
    }

    @Override
    public ProcessInstance startProcess(String processID) {
	return ksession.startProcess(processID);
    }

    @Override
    public void startProces(String processID, Map<String, Object> dataInput) {
	if (processID == null || "".equals(processID)) {
	    logger.error("processID is null");
	} else {
	    ksession.startProcess(processID, dataInput);
	}
    }

    @Override
    public List<Task> getTasksAssigned(String userID) {
	BlockingTaskSummaryResponseHandler responseHandler = new BlockingTaskSummaryResponseHandler();
	taskClient.getTasksAssignedAsPotentialOwner(userID, "en-UK", responseHandler);
	List<Task> result = new ArrayList<Task>();
	if (!responseHandler.getResults().isEmpty()) {
	    for (TaskSummary ts : responseHandler.getResults()) {
		BlockingGetTaskResponseHandler taskResponseHandler = new BlockingGetTaskResponseHandler();
		taskClient.getTask(ts.getId(), taskResponseHandler);
		result.add(taskResponseHandler.getTask());
	    }
	} else {
	    logger.info("No tasks found for this userID");
	}
	return result;
    }

    @Override
    public List<Task> getTasksOwned(String userID) {
	BlockingTaskSummaryResponseHandler responseHandler = new BlockingTaskSummaryResponseHandler();
	taskClient.getTasksOwned(userID, "en-UK", responseHandler);
	List<Task> result = new ArrayList<Task>();
	if (!responseHandler.getResults().isEmpty()) {
	    for (TaskSummary ts : responseHandler.getResults()) {
		BlockingGetTaskResponseHandler taskResponseHandler = new BlockingGetTaskResponseHandler();
		taskClient.getTask(ts.getId(), taskResponseHandler);
		result.add(taskResponseHandler.getTask());
	    }
	} else {
	    logger.info("No tasks found for this userID");
	}
	return result;
    }

    @Override
    public List<Task> getTasksByRole(String userID, String processID) {
	BlockingTaskSummaryResponseHandler responseHandler = new BlockingTaskSummaryResponseHandler();
	taskClient.getTasksAssignedAsPotentialOwner(userID, "en-UK", responseHandler);

	List<Task> result = new ArrayList<Task>();
	if (!responseHandler.getResults().isEmpty()) {
	    for (TaskSummary ts : responseHandler.getResults()) {
		/* tests if a task is for a defined process */
		BlockingGetTaskResponseHandler taskResponseHandler = new BlockingGetTaskResponseHandler();
		taskClient.getTask(ts.getId(), taskResponseHandler);
		result.add(taskResponseHandler.getTask());
	    }
	} else {
	    logger.info("No tasks found for this userID");
	}
	return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getTaskContent(Task task) {
	logger.info("Get task content from Task.class");

	TaskData taskData = task.getTaskData();
	BlockingGetContentResponseHandler handlerC = new BlockingGetContentResponseHandler();
	taskClient.getContent(taskData.getDocumentContentId(), handlerC);
	Content content = handlerC.getContent();
	ByteArrayInputStream bais = new ByteArrayInputStream(content.getContent());
	try {
	    ObjectInputStream is = new ObjectInputStream(bais);
	    Object obj = null;
	    while ((obj = is.readObject()) != null) {
		return (Map<String, Object>) obj;
	    }
	} catch (Exception e) {
	    logger.error("There was an error reading task input.");
	    e.printStackTrace();
	    return null;
	}
	return null;
    }

    @Override
    public void startTask(String userID, Task task) {
	BlockingTaskOperationResponseHandler responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.start(task.getId(), userID, responseHandler);
	responseHandler.waitTillDone(1000);
    }

    @Override
    public void delegateTask(String userID, String targetUserID, Task task) {
	BlockingTaskOperationResponseHandler responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.delegate(task.getId(), userID, targetUserID, responseHandler);
	responseHandler.waitTillDone(1000);
    }

    @Override
    public void completeTask(String userID, Task task, Map<String, Object> dataInput) {

	ContentData contentData = null;
	if (dataInput != null) {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream outs;
	    try {
		outs = new ObjectOutputStream(bos);
		outs.writeObject(dataInput);
		outs.close();
		contentData = new ContentData();
		contentData.setContent(bos.toByteArray());
		contentData.setAccessType(AccessType.Inline);
	    } catch (IOException e) {
		logger.error("There was an error writing task input.");
		e.printStackTrace();
	    }
	}

	/* Complete a Task with the injected ContentData */
	BlockingTaskOperationResponseHandler responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.complete(task.getId(), userID, contentData, responseHandler);
	responseHandler.waitTillDone(1000);

    }

    @Override
    public void startAndCompleteTask(String userID, Task task) {
	/* start Task by its ID */
	BlockingTaskOperationResponseHandler responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.start(task.getId(), userID, responseHandler);
	responseHandler.waitTillDone(1000);

	/* Complete a Task with the injected ContentData */
	responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.complete(task.getId(), userID, null, responseHandler);
	responseHandler.waitTillDone(1000);
    }

    @Override
    public void startAndCompleteTask(String userID, Task task, Map<String, Object> dataInput) {
	/* start Task by its ID */
	BlockingTaskOperationResponseHandler responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.start(task.getId(), userID, responseHandler);
	responseHandler.waitTillDone(1000);

	/* Create ContentData needed for the Task */
	ContentData contentData = null;
	if (dataInput != null) {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream outs;
	    try {
		outs = new ObjectOutputStream(bos);
		outs.writeObject(dataInput);
		outs.close();
		contentData = new ContentData();
		contentData.setContent(bos.toByteArray());
		contentData.setAccessType(AccessType.Inline);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	/* Complete a Task with the injected ContentData */
	responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.complete(task.getId(), userID, contentData, responseHandler);
	responseHandler.waitTillDone(1000);
    }

    @Override
    public void startAndCompleteTask(String userID, String processID, Long taskID, Map<String, Object> dataInput) {
	/* start Task by its ID */
	BlockingTaskOperationResponseHandler responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.start(taskID, userID, responseHandler);
	responseHandler.waitTillDone(1000);

	/* Create ContentData needed for the Task */
	ContentData contentData = null;
	if (dataInput != null) {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream outs;
	    try {
		outs = new ObjectOutputStream(bos);
		outs.writeObject(dataInput);
		outs.close();
		contentData = new ContentData();
		contentData.setContent(bos.toByteArray());
		contentData.setAccessType(AccessType.Inline);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	/* Complete a Task with the injected ContentData */
	responseHandler = new BlockingTaskOperationResponseHandler();
	taskClient.complete(taskID, userID, contentData, responseHandler);
	responseHandler.waitTillDone(1000);
    }

    public int countTasksByTaskName(String taskName) {
	BlockingQueryGenericResponseHandler responseHandler = new BlockingQueryGenericResponseHandler();
	taskClient.query("select count(t) from Task t left join t.names as name where name.text = '" + taskName + "'",
		Integer.MAX_VALUE, 0, responseHandler);
	List<?> tasks = responseHandler.getResults();
	return (Integer) (tasks == null ? 0 : tasks.get(0));
    }

    @Override
    public List<Task> getTaskList(String taskName, String userID) {
	BlockingQueryGenericResponseHandler responseHandler = new BlockingQueryGenericResponseHandler();
	taskClient.query(
		"select t from Task t left join t.names as name, OrganizationalEntity potentialOwners where name.text = '"
			+ taskName + "' and potentialOwners.id = '" + userID
			+ "' and potentialOwners in elements ( t.peopleAssignments.potentialOwners ) "
			+ "and t.taskData.status in ('Created', 'Ready', 'Reserved','Suspended')", Integer.MAX_VALUE,
		0, responseHandler);
	return (List<Task>) responseHandler.getResults();
    }

    @Override
    public List<Task> getStartedTaskList(String taskName, String userID) {
	BlockingQueryGenericResponseHandler responseHandler = new BlockingQueryGenericResponseHandler();
	taskClient.query(
		"select t from Task t left join t.names as name, OrganizationalEntity potentialOwners where name.text = '"
			+ taskName + "' and potentialOwners.id = '" + userID
			+ "' and potentialOwners in elements ( t.peopleAssignments.potentialOwners ) "
			+ "and t.taskData.status in ('InProgress')", Integer.MAX_VALUE, 0, responseHandler);
	return (List<Task>) responseHandler.getResults();
    }

    @Override
    public Long getCountTaskList(String taskName, String userID) {
	BlockingQueryGenericResponseHandler responseHandler = new BlockingQueryGenericResponseHandler();
	taskClient.query(
		"select count(t) from Task t left join t.names as name, OrganizationalEntity potentialOwners where name.text = '"
			+ taskName + "' and potentialOwners.id = '" + userID
			+ "' and potentialOwners in elements ( t.peopleAssignments.potentialOwners ) "
			+ "and t.taskData.status in ('Created', 'Ready', 'Reserved', 'Suspended')", Integer.MAX_VALUE,
		0, responseHandler);
	List<?> tasks = responseHandler.getResults();
	return (Long) (tasks == null ? 0 : tasks.get(0));
    }

    @Override
    public Long getCountStartedTaskList(String taskName, String userID) {
	BlockingQueryGenericResponseHandler responseHandler = new BlockingQueryGenericResponseHandler();
	taskClient.query(
		"select count(t) from Task t left join t.names as name, OrganizationalEntity potentialOwners where name.text = '"
			+ taskName + "' and potentialOwners.id = '" + userID
			+ "' and potentialOwners in elements ( t.peopleAssignments.potentialOwners ) "
			+ "and t.taskData.status in ('InProgress')", Integer.MAX_VALUE, 0, responseHandler);
	List<?> tasks = responseHandler.getResults();
	return (Long) (tasks == null ? 0 : tasks.get(0));
    }

    @Override
    public void addDataInput(Map<String, Object> dataInput, Enum<?> key, Object value) {
	logger.debug("Data => key: " + key + " value: " + value);
	dataInput.put(key.toString(), value);
    }

    @Override
    public void addDataInput(Map<String, Object> dataInput, String key, Object value) {
	logger.debug("Data => key: " + key + " value: " + value);
	dataInput.put(key.toString(), value);
    }

}
