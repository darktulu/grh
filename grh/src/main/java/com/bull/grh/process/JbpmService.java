package com.bull.grh.process;

import java.util.List;
import java.util.Map;

import org.drools.runtime.process.ProcessInstance;
import org.jbpm.task.Task;

/**
 * <p>
 * Generic class to query the knowledge base and interact with the defined
 * processes and running ones
 * </p>
 * 
 * @author Mohamed.Kecha
 * 
 */
public interface JbpmService {

    /**
     * Start process with given process ID
     * 
     * @param processID
     *            use ProcessID enumerations
     */
    public void startProces(String processID);

    /**
     * Start process with given processID and returns the processInstance
     * 
     * @param processID
     * @return processInstance
     */
    public ProcessInstance startProcess(String processID);

    /**
     * Start process with given process ID and processInstance InputData
     * 
     * @param processID
     *            String : use String enumerations
     * @param dataInput
     *            Map with data needed to begin the process
     */
    public void startProces(String processID, Map<String, Object> dataInput);

    /**
     * Gets all the tasks for the given userID
     * 
     * @param userID
     *            String : related to a user use ROLE_
     * @return List<Task> All tasks for the given userID
     */
    // TODO comment assigned = actif
    public List<Task> getTasksAssigned(String userID);

    // TODO comment owned = actif, completed ...
    public List<Task> getTasksOwned(String userID);

    /**
     * Gets all the tasks for the given userID of a specific process
     * 
     * @param userID
     *            String : related to a user use ROLE_
     * @param processID
     *            String : use String enumerations
     * @return List<Task> All tasks of the given processID and userID
     */
    public List<Task> getTasksByRole(String userID, String processID);

    /**
     * Gets all the data in a Map of the given Task, beware of null return and
     * some inputStrem related exceptions
     * 
     * @param task
     *            Task : chosen task to get content from
     * @return Map<String,Object> : all content data of the task
     */
    public Map<String, Object> getTaskContent(Task task);

    /**
     * Starts and completes a chosen task
     * 
     * @param userID
     *            String : the userID of the user starting and completing this
     *            task
     * @param task
     *            Task : chosen task to be started and completed
     */
    public void startAndCompleteTask(String userID, Task task);

    /**
     * Starts and completes a chosen task
     * 
     * @param userID
     *            String : the userID of the user starting and completing this
     *            task
     * @param task
     *            Task : chosen task to be started and completed
     * @param dataInput
     *            Map<String, Object> : needed data to complete this task
     */
    public void startAndCompleteTask(String userID, Task task, Map<String, Object> dataInput);

    // TODO comment
    public List<Task> getTaskList(String taskName, String userID);

    // TODO comment
    public Long getCountTaskList(String taskName, String userID);

    /**
     * Starts and completes a chosen task
     * 
     * @param userID
     *            String : the userID of the user starting and completing this
     *            task
     * @param taskID
     *            Long : chosen task id to be started and completed
     * @param dataInput
     *            Map<String, Object> : needed data to complete this task
     */
    public void startAndCompleteTask(String userID, String processID, Long taskID, Map<String, Object> dataInput);

    public int countTasksByTaskName(String taskName);

    public void addDataInput(Map<String, Object> dataInput, Enum<?> key, Object value);

    public void addDataInput(Map<String, Object> dataInput, String key, Object value);

    public Long getCountStartedTaskList(String taskName, String userID);

    public List<Task> getStartedTaskList(String taskName, String userID);

    /**
     * Starts a task
     * 
     * @param userID
     *            String
     * @param task
     *            Task
     */
    public void startTask(String userID, Task task);

    //TODO comment ABSOLUMENT
    public void delegateTask(String userID, String targetUserID, Task task);

    public void completeTask(String userID, Task task, Map<String, Object> dataInput);
}
