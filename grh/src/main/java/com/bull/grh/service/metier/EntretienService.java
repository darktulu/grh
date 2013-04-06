package com.bull.grh.service.metier;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.jbpm.task.Task;

import com.bull.grh.view.metier.vo.EntretienVO;

public interface EntretienService {
    void startCETask(Task task);

    void startRHTask(Task task);

    void completeTaskCE(Task task, @Valid EntretienVO entretien);

    void completeTaskRH(Task task, @Valid EntretienVO entretien);

    Map<EntretienVO, Task> loadCETaskList();

    Map<EntretienVO, Task> loadRHTaskList();

    Map<EntretienVO, Task> loadRHStartedTaskList();

    Map<EntretienVO, Task> loadCEStartedTaskList();

    Long getCountCETaskList();

    Long getCountCEStartedTaskList();

    Long getCountRHTaskList();
    
    Long getCountCEEntretiens();

    Long getCountRHStartedTaskList();

    void startProcess(EntretienVO entretien);
    
    List<EntretienVO> loadEntretiensCE();

    void startAndCompleteTask(Task task, EntretienVO entretien);
}
