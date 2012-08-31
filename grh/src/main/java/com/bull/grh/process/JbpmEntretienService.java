package com.bull.grh.process;

import java.util.List;
import java.util.Map;

import org.jbpm.task.Task;

import com.bull.grh.view.metier.vo.EntretienVO;

public interface JbpmEntretienService {
    // TODO COMMENT
    public void completeTask(String userID, Task task, EntretienVO entretien);

    public List<Task> getCETaskList(String role);

    public Long getCountCETaskList(String role);

    public List<Task> getCEStartedTaskList(String role);

    public Long getCountCEStartedTaskList(String role);

    public List<Task> getRHTaskList(String role);

    public Long getCountRHTaskList(String role);

    public List<Task> getRHStartedTaskList(String role);

    public Long getCountRHStartedTaskList(String role);

    public Map<String, Object> getTaskContent(Task task);

    public void startTask(String userID, Task task);

    public void delegateTask(String userID, String targetUserID, Task task);

    public void startProces(String userID, EntretienVO entretien);

    public void startAndCompleteTask(String role, Task task, EntretienVO entretien);

}
