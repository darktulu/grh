package com.bull.grh.service.metier;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.jbpm.task.Task;

import com.bull.grh.view.metier.vo.EntretienVO;

public interface EntretienService {

    public void startCETask(Task task);

    public void startRHTask(Task task);

    public void completeTaskCE(Task task, @Valid EntretienVO entretien);

    public void completeTaskRH(Task task, @Valid EntretienVO entretien);

    public Map<EntretienVO, Task> loadCETaskList();

    public Map<EntretienVO, Task> loadRHTaskList();

    public Map<EntretienVO, Task> loadRHStartedTaskList();

    public Map<EntretienVO, Task> loadCEStartedTaskList();

    public Long getCountCETaskList();

    public Long getCountCEStartedTaskList();

    public Long getCountRHTaskList();
    
    public Long getCountCEEntretiens();

    public Long getCountRHStartedTaskList();

    public void startProcess(EntretienVO entretien);
    
    public List<EntretienVO> loadEntretiensCE();

    public void startAndCompleteTask(Task task, EntretienVO entretien);

}
