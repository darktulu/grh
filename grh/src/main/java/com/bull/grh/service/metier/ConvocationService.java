package com.bull.grh.service.metier;

import java.util.Map;

import javax.validation.Valid;

import org.jbpm.task.Task;

import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;

public interface ConvocationService {
    // TODO comment
    public Long getCountStartedTaskList();

    public Long getCountTaskList();

    public Map<CandidatureVO, Task> loadStartedTaskList();

    public Map<CandidatureVO, Task> loadTaskList();

    public void completeConvocation(Task task, @Valid CandidatureVO candidature, @Valid EntretienVO entretien);

    public void cancelConvocation(Task task, CandidatureVO candidature);

    public void startConvocation(Task task);

}
