package com.bull.grh.service.metier.impl;

import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EtatCandidature;
import com.bull.grh.domaine.types.EtatEntretien;
import com.bull.grh.domaine.types.EtatEvaluation;
import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.repos.metier.CandidatureDao;
import com.bull.grh.repos.metier.EntretienDao;
import com.bull.grh.service.metier.ConvocationService;
import com.bull.grh.service.utils.AuthenticationService;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.EntretienVO;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("convocationService")
public class ConvocationServiceImpl implements ConvocationService {
    @Inject
    public AuthenticationService authenticationService;
    @Inject
    private CandidatureDao candidatureDao;
    @Inject
    private DozerBeanMapper mapper;
    @Inject
    private EntretienDao entretienDao;
    @Inject
    private TaskService taskService;
    @Inject
    private RuntimeService runtimeService;

    @Override
    public void cancelConvocation(CandidatureVO candidature) {
        // change the state of the appointment
        candidature.setEtatCandidature(EtatCandidature.COMPLETED);

        // update the state of the Entities
        candidatureDao.save(mapper.map(candidature, Candidature.class));

        // complete task without any parameters (it will end the process for
        // this candidate)
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(ProcessConst.PROCESS_ID_CONVOCATION)
                .processVariableValueEquals(ProcessConst.CONVOCATION_CANDIDATURE, candidature.getId())
                .singleResult();
        if (task == null) return;

        Map<String, Object> params  = new HashMap<String, Object>();
        params.put(ProcessConst.CONVOCATION_DISPONIBLE, false);

        taskService.complete(task.getId(), params);
    }

    @Override
    public void startConvocation(CandidatureVO candidatureVO) {
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(ProcessConst.PROCESS_ID_CONVOCATION)
                .processVariableValueEquals(ProcessConst.CONVOCATION_CANDIDATURE, candidatureVO.getId())
                .singleResult();
        taskService.claim(task.getId(), authenticationService.getConnectedPersonne().getEmail());
    }

    @Override
    public void completeConvocation(CandidatureVO candidature,
                                    EntretienVO entretien) {

        // Persist the appointment with new changes and creates evaluation for
        // this appointment
        entretien.setCandidature(candidature);
        entretien.setEtat(EtatEntretien.NEW);
        entretien.getEvaluation().setEtatEvaluation(EtatEvaluation.NEW);

        Entretien newEntretien = mapper.map(entretien, Entretien.class);
        newEntretien = entretienDao.save(newEntretien);
        entretien = mapper.map(newEntretien, EntretienVO.class);

        // continues the process by injecting the appointment
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(ProcessConst.PROCESS_ID_CONVOCATION)
                .processVariableValueEquals(ProcessConst.CONVOCATION_CANDIDATURE, candidature.getId())
                .singleResult();
        if (task == null) return;

        Map<String, Object> params  = new HashMap<String, Object>();
        params.put(ProcessConst.CONVOCATION_DISPONIBLE, true);
        params.put(ProcessConst.CONVOCATION_ENTRETIEN, entretien.getId());

        taskService.complete(task.getId(), params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidatureVO> loadTaskList() {
        CandidatureVO candidatureVO;
        List<CandidatureVO> result = new ArrayList<CandidatureVO>();
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey(ProcessConst.PROCESS_ID_CONVOCATION)
                .taskCandidateGroup("ROLE_RH")
                .taskDefinitionKey(ProcessConst.CONVOCATION_TASK_CONVOCATION).list();

        for (Task task : tasks) {
            Long candidatureId = (Long) runtimeService.getVariable(task.getExecutionId(), ProcessConst.CONVOCATION_CANDIDATURE);
            candidatureVO = mapper.map(candidatureDao.findOne(candidatureId), CandidatureVO.class);

            result.add(candidatureVO);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidatureVO> loadStartedTaskList() {
        CandidatureVO candidatureVO;
        List<CandidatureVO> result = new ArrayList<CandidatureVO>();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(authenticationService.getConnectedPersonne().getEmail())
                .processDefinitionKey(ProcessConst.PROCESS_ID_CONVOCATION)
                .taskDefinitionKey(ProcessConst.CONVOCATION_TASK_CONVOCATION).list();

        for (Task task : tasks) {
            Long candidatureId = (Long) runtimeService.getVariable(task.getExecutionId(), ProcessConst.CONVOCATION_CANDIDATURE);
            candidatureVO = mapper.map(candidatureDao.findOne(candidatureId), CandidatureVO.class);

            result.add(candidatureVO);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountTaskList() {
        return taskService.createTaskQuery()
                .taskCandidateGroup("ROLE_RH")
                .processDefinitionKey(ProcessConst.PROCESS_ID_CONVOCATION)
                .taskDefinitionKey(ProcessConst.CONVOCATION_TASK_CONVOCATION).list().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountStartedTaskList() {
        return taskService.createTaskQuery()
                .taskAssignee(authenticationService.getConnectedPersonne().getEmail())
                .processDefinitionKey(ProcessConst.PROCESS_ID_CONVOCATION)
                .taskDefinitionKey(ProcessConst.CONVOCATION_TASK_CONVOCATION).list().size();
    }
}
