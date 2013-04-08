package com.bull.grh.service.metier;

import com.bull.grh.view.metier.vo.EntretienVO;

import javax.validation.Valid;
import java.util.List;

public interface EntretienService {
    void startRHTask(@Valid EntretienVO entretien);

    void completeTaskRH(@Valid EntretienVO entretien);

    List<EntretienVO> loadCETaskList();

    List<EntretienVO> loadRHTaskList();

    List<EntretienVO> loadRHStartedTaskList();

    Integer getCountCETaskList();

    Integer getCountRHTaskList();

    Integer getCountCEEntretiens();

    Integer getCountRHStartedTaskList();

    void startProcess(EntretienVO entretien);

    List<EntretienVO> loadEntretiensCE();

    void startAndCompleteTask(EntretienVO entretien);
}
