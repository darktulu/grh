package com.bull.grh.process;

import java.util.List;
import java.util.Map;

import org.jbpm.task.Task;

import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;

public interface JbpmDemandeService {

    //TODO comment
    public Map<String, Object> getTaskContent(Task task);

    public void startTask(String userID, Task task);

    public void delegateTask(String userID, String targetUserID, Task task);
    
    public Long getCountTaskList(String taskName, String role);
    
    public Long getCountStartedTaskList(String taskName, String role);
    
    public List<Task> getTaskList(String taskName, String role);
    
    public List<Task> getStartedTaskList(String taskName, String role);

    void startAndCompleteTask(String userID, Task task, DemandeVO demande);

    void completeTask(String userID, Task task, DemandeVO demande);

    void startAndCompleteTask(String userID, Task task, DemandeVO demande, List<CandidatureVO> candidatureList);

    void completeTask(String userID, Task task, DemandeVO demande, List<CandidatureVO> candidatureList);

    void startProcess(String userID, DemandeVO demande);
}
