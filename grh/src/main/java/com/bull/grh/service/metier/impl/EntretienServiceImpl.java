package com.bull.grh.service.metier.impl;

import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EtatEntretien;
import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.repos.metier.EntretienDao;
import com.bull.grh.service.metier.EntretienService;
import com.bull.grh.view.metier.vo.EntretienVO;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.dozer.DozerBeanMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bull.grh.domaine.types.ProcessConst.*;

@Transactional
@Service("entretienService")
public class EntretienServiceImpl implements EntretienService {
    @Inject
    private EntretienDao entretienDao;
    @Inject
    private DozerBeanMapper mapper;
    @Inject
    private RuntimeService runtimeService;
    @Inject
    private TaskService taskService;

    @Override
    public void startProcess(EntretienVO entretien) {
        entretien.setEtat(EtatEntretien.ONGOING);
        entretienDao.save(mapper.map(entretien, Entretien.class));

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(APPOINTMENT_USERNAME_CE, SecurityContextHolder.getContext().getAuthentication().getName());
        params.put(APPOINTMENT_ENTRETIEN, entretien.getId());

        runtimeService.startProcessInstanceByKey(PROCESS_ID_ENTRETIEN, entretien.getId().toString(), params);
    }

    @Override
    public void startAndCompleteTask(EntretienVO entretien) {
        // save the evaluation for this appointment
        entretienDao.save(mapper.map(entretien, Entretien.class));

        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(entretien.getId().toString())
                .processDefinitionKey(PROCESS_ID_ENTRETIEN)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .singleResult();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(APPOINTMENT_DECISION, entretien.getDecisionEntretien().toString());

        taskService.complete(task.getId(), params);
    }

    @Override
    public void startRHTask(EntretienVO entretien) {
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(entretien.getId().toString())
                .processDefinitionKey(PROCESS_ID_ENTRETIEN)
                .taskCandidateGroup("ROLE_RH")
                .singleResult();

        taskService.claim(task.getId(), SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public void completeTaskRH(EntretienVO entretien) {
        // Persist the appointment with new evaluation's changes
        entretien.setEtat(EtatEntretien.COMPLETED);

        Entretien newEntretien = mapper.map(entretien, Entretien.class);
        newEntretien = entretienDao.save(newEntretien);
        entretien = mapper.map(newEntretien, EntretienVO.class);

        // continues the process by injecting the appointment
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(entretien.getId().toString())
                .processDefinitionKey(PROCESS_ID_ENTRETIEN)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .singleResult();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(APPOINTMENT_CANDIDATURE, entretien.getCandidature().getId());
        params.put(APPOINTMENT_DECISION, entretien.getDecisionEntretien().toString());

        taskService.complete(task.getId(), params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntretienVO> loadCETaskList() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey(APPOINTMENT_TASK_CE)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .list();

        EntretienVO entretien;
        List<EntretienVO> list = new ArrayList<EntretienVO>();
        for (Task task : tasks) {
            Long entretienId = (Long) runtimeService.getVariable(task.getExecutionId(), APPOINTMENT_ENTRETIEN);
            entretien = mapper.map(entretienDao.findOne(entretienId), EntretienVO.class);
            list.add(entretien);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntretienVO> loadRHTaskList() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey(APPOINTMENT_TASK_RH)
                .taskCandidateGroup("ROLE_RH")
                .list();

        EntretienVO entretien;
        List<EntretienVO> list = new ArrayList<EntretienVO>();
        for (Task task : tasks) {
            Long entretienId = (Long) runtimeService.getVariable(task.getExecutionId(), APPOINTMENT_ENTRETIEN);
            entretien = mapper.map(entretienDao.findOne(entretienId), EntretienVO.class);
            list.add(entretien);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntretienVO> loadRHStartedTaskList() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey(APPOINTMENT_TASK_RH)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .list();

        EntretienVO entretien;
        List<EntretienVO> list = new ArrayList<EntretienVO>();
        for (Task task : tasks) {
            Long entretienId = (Long) runtimeService.getVariable(task.getExecutionId(), APPOINTMENT_ENTRETIEN);
            entretien = mapper.map(entretienDao.findOne(entretienId), EntretienVO.class);
            list.add(entretien);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntretienVO> loadEntretiensCE() {
        List<EntretienVO> result = new ArrayList<EntretienVO>();
        for (Entretien entretien : entretienDao.findByPersonneUsernameAndEtat(SecurityContextHolder.getContext()
                .getAuthentication().getName(), EtatEntretien.NEW)) {
            result.add(mapper.map(entretien, EntretienVO.class));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountCETaskList() {
        return taskService.createTaskQuery()
                .taskDefinitionKey(APPOINTMENT_TASK_CE)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .list().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountRHTaskList() {
        return taskService.createTaskQuery()
                .taskDefinitionKey(APPOINTMENT_TASK_RH)
                .taskCandidateGroup("ROLE_RH")
                .list().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountRHStartedTaskList() {
        return taskService.createTaskQuery()
                .taskDefinitionKey(APPOINTMENT_TASK_RH)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .list().size();
    }

    @Override
    public Integer getCountCEEntretiens() {
        return entretienDao.findByPersonneUsernameAndEtat(SecurityContextHolder.getContext().getAuthentication().getName(),
                EtatEntretien.NEW).size();
    }
}
