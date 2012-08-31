package com.bull.grh.process;

import java.util.List;
import java.util.Map;

import org.jbpm.task.Task;

import com.bull.grh.view.metier.vo.EntretienVO;

public interface JbpmConvocationService {

    public void startAndCompleteTask(String userID, Task task, EntretienVO entretien, boolean disponible);

    public void completeTask(String userID, Task task, EntretienVO entretien, boolean disponible);

    public void startTask(String userID, Task task);

    public void delegateTask(String userID, String targetUserID, Task task);

    public List<Task> getTaskList(String role);

    public Long getCountTaskList(String role);

    public List<Task> getStartedTaskList(String role);

    public Long getCountStartedTaskList(String role);

    public Map<String, Object> getTaskContent(Task task);

}
