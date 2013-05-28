package com.bull.grh.service.metier.impl;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.Demande;
import com.bull.grh.domaine.DossierCandidature;
import com.bull.grh.domaine.types.EtatDemande;
import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.repos.admin.PersonneDao;
import com.bull.grh.repos.metier.CandidatureDao;
import com.bull.grh.repos.metier.DemandeDao;
import com.bull.grh.service.exception.AlreadyHaveCandidatureException;
import com.bull.grh.service.exception.CandidatureNotFoundException;
import com.bull.grh.service.exception.DemandeHaveNoCandidatureException;
import com.bull.grh.service.exception.NoDemandeSelectedException;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.dozer.DozerBeanMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@Transactional
@Service("demandeService")
public class DemandeServiceImpl implements DemandeService {

    @Inject
    private CandidatureDao candidatureDao;
    @Inject
    private DozerBeanMapper mapper;
    @Inject
    private DemandeDao demandeDao;
    @Inject
    private PersonneDao personneDao;
    @Inject
    private RuntimeService runtimeService;
    @Inject
    private TaskService taskService;

    @Override
    public void createDemande(DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.NEW);
        Demande toSave = mapper.map(demande, Demande.class);
        toSave.setPersonne(personneDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        toSave.setDate(new Date());
        demandeDao.save(toSave);
    }

    @Override
    public void startTaskDemandeRH(DemandeVO demande) {
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(demande.getId().toString())
                .taskCandidateGroup("ROLE_RH").singleResult();
        taskService.claim(task.getId(), SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public void startTaskDemandeOP(DemandeVO demande) {
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(demande.getId().toString())
                .taskCandidateGroup("ROLE_RH").singleResult();
        taskService.claim(task.getId(), SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemandeVO> loadDemandesNouveau() {
        List<DemandeVO> list = new ArrayList<DemandeVO>();
        List<Demande> demandes = demandeDao.findByEtatDemandeIn(Arrays.asList(EtatDemande.NEW, EtatDemande.REJECTED));
        for (Demande demande : demandes) {
            list.add(mapper.map(demande, DemandeVO.class));
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemandeVO> loadDemandesTraite() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_OP_CHOICE)
                .taskUnassigned()
                .list();

        DemandeVO demandeVO;
        List<DemandeVO> list = new ArrayList<DemandeVO>();
        for (Task task : tasks) {
            Long demandeId = (Long) runtimeService.getVariable(task.getExecutionId(), ProcessConst.DEMANDE_DEMANDE);
            demandeVO = mapper.map(demandeDao.findOne(demandeId), DemandeVO.class);
            list.add(demandeVO);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemandeVO> loadStartedDemandesTraite() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_OP_CHOICE)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .list();

        DemandeVO demandeVO;
        List<DemandeVO> list = new ArrayList<DemandeVO>();
        for (Task task : tasks) {
            Long demandeId = (Long) runtimeService.getVariable(task.getExecutionId(), ProcessConst.DEMANDE_DEMANDE);
            demandeVO = mapper.map(demandeDao.findOne(demandeId), DemandeVO.class);
            list.add(demandeVO);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemandeVO> loadDemandesSoumise() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_RH_VALIDATION)
                .taskCandidateGroup("ROLE_RH")
                .list();

        DemandeVO demandeVO;
        List<DemandeVO> list = new ArrayList<DemandeVO>();
        for (Task task : tasks) {
            Long demandeId = (Long) runtimeService.getVariable(task.getExecutionId(), ProcessConst.DEMANDE_DEMANDE);
            demandeVO = mapper.map(demandeDao.findOne(demandeId), DemandeVO.class);
            list.add(demandeVO);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemandeVO> loadStartedDemandesSoumise() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_RH_VALIDATION)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .list();

        DemandeVO demandeVO;
        List<DemandeVO> list = new ArrayList<DemandeVO>();
        for (Task task : tasks) {
            Long demandeId = (Long) runtimeService.getVariable(task.getExecutionId(), ProcessConst.DEMANDE_DEMANDE);
            demandeVO = mapper.map(demandeDao.findOne(demandeId), DemandeVO.class);
            list.add(demandeVO);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountStartedDemandesTraite() {
        return taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_OP_CHOICE)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .list().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountDemandesTraite() {
        return taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_OP_CHOICE)
                .taskUnassigned()
                .list().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountDemandesSoumise() {
        return taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_RH_VALIDATION)
                .taskCandidateGroup("ROLE_RH")
                .list().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountStartedDemandesSoumise() {
        return taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_RH_VALIDATION)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .list().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountDemandesNouveau() {
        return demandeDao.findByEtatDemandeIn(Arrays.asList(EtatDemande.NEW, EtatDemande.REJECTED)).size();
    }

    @Override
    public void sendDemande(DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.SENT);
        demandeDao.save(mapper.map(demande, Demande.class));

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ProcessConst.DEMANDE_DEMANDE, demande.getId());
        params.put(ProcessConst.DEMANDE_OWNER, SecurityContextHolder.getContext().getAuthentication().getName());
        params.put(ProcessConst.DEMANDE_ANNULATION, false);

        // proceed by completing the task
        runtimeService.startProcessInstanceByKey(ProcessConst.PROCESS_ID_DEMANDE_RECRUTEMENT, demande.getId().toString(), params);
    }

    @Override
    public void deleteDemande(DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.DELETED);
        demandeDao.delete(mapper.map(demande, Demande.class));
    }

    @Override
    public void rejectDemande(DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.REJECTED);
        demandeDao.save(mapper.map(demande, Demande.class));

        // proceed by completing the task
        Task task = taskService.createTaskQuery()
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_RH_VALIDATION)
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .processInstanceBusinessKey(demande.getId().toString())
                .singleResult();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ProcessConst.DEMANDE_DEMANDE, demande.getId());
        params.put(ProcessConst.DEMANDE_VALIDE, false);

        taskService.complete(task.getId(), params);
    }

    @Override
    public void rejectDemandeAfterAccepting(DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.REJECTED);
        demandeDao.save(mapper.map(demande, Demande.class));

        // verify if the demand has some candidatures saved if so delete
        // them from the database
        List<Candidature> candidatures = candidatureDao.findByDemand(demande.getId());
        if (candidatures != null && candidatures.size() > 0) {
            for (Candidature candidature : candidatures) {
                candidatureDao.delete(candidature);
            }
        }

        // proceed by completing the task
        Task task = taskService.createTaskQuery()
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .processInstanceBusinessKey(demande.getId().toString())
                .singleResult();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ProcessConst.DEMANDE_ANNULATION, true);

        taskService.complete(task.getId(), params);
    }

    @Override
    public void completeDemande(DemandeVO demande) throws DemandeHaveNoCandidatureException {

        List<Candidature> candidatures = candidatureDao.findByDemand(demande.getId());

        if (candidatures == null || candidatures.isEmpty())
            throw new DemandeHaveNoCandidatureException();

        // Change the status of the Demand to TRAITE and persist the
        // entity
        demande.setEtatDemande(EtatDemande.APPROVED);
        demandeDao.save(mapper.map(demande, Demande.class));

        // proceed by completing the task
        Task task = taskService.createTaskQuery()
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .processInstanceBusinessKey(demande.getId().toString())
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_RH_VALIDATION)
                .singleResult();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ProcessConst.DEMANDE_VALIDE, true);

        taskService.complete(task.getId(), params);
    }

    @Override
    public void completeDemande(DemandeVO demande, List<CandidatureVO> candidatureList)
            throws DemandeHaveNoCandidatureException {
        List<Candidature> candidatures = candidatureDao.findByDemand(demande.getId());

        if (candidatures == null || candidatures.isEmpty())
            throw new DemandeHaveNoCandidatureException();
        // Change the status of the Demand to COMPLETE and persist the
        // entity
        demande.setEtatDemande(EtatDemande.COMPLETED);
        demandeDao.save(mapper.map(demande, Demande.class));

        // proceed by completing the task
        // start a calling process for every candidate in the the demand
        Task task = taskService.createTaskQuery()
                .taskAssignee(SecurityContextHolder.getContext().getAuthentication().getName())
                .processInstanceBusinessKey(demande.getId().toString())
                .taskDefinitionKey(ProcessConst.DEMANDE_TASK_OP_CHOICE)
                .singleResult();

        Map<String, Object> params = new HashMap<String, Object>();
        List<Long> candidaturesId = new ArrayList<Long>();
        for (Candidature candidature : candidatures) {
            candidaturesId.add(candidature.getId());
        }
        params.put(ProcessConst.DEMANDE_CANDIDATURE_LIST, candidaturesId);

        taskService.complete(task.getId(), params);
    }

    @Override
    public void addCandidateToDemand(CandidatVO candidat, DemandeVO demand) throws AlreadyHaveCandidatureException, NoDemandeSelectedException {
        if (demand.getId() == null)
            throw new NoDemandeSelectedException();

        List<Candidature> candidatures = candidatureDao.findByDemandeIdAndCandidatUsername(demand.getId(),
                candidat.getUsername());
        if (candidatures != null && candidatures.size() > 0)
            throw new AlreadyHaveCandidatureException();

        // create the candidature to this demand for the candidate
        Candidature candidature = new Candidature();
        candidature.setCandidat(mapper.map(candidat, Candidat.class));
        candidature.setDemande(mapper.map(demand, Demande.class));
        candidature.setDossierCandidature(new DossierCandidature());

        // save the new candidature to database
        candidatureDao.save(candidature);
    }

    @Override
    public void removeCandidateFromDemand(CandidatVO candidat, DemandeVO demand) throws CandidatureNotFoundException {
        List<Candidature> candidatures = candidatureDao.findByDemandeIdAndCandidatUsername(demand.getId(),
                candidat.getUsername());
        if (candidatures == null || candidatures.isEmpty())
            throw new CandidatureNotFoundException();
        // delete this candidature from database
        candidatureDao.delete(candidatures.get(0));
    }

    @Override
    public List<CandidatureVO> loadCandidatures(DemandeVO demande) {
        List<Candidature> list = candidatureDao.findByDemand(demande.getId());
        List<CandidatureVO> result = new ArrayList<CandidatureVO>();
        for (Candidature candidature : list) {
            result.add(mapper.map(candidature, CandidatureVO.class));
        }
        return result;
    }

    @Override
    public Long getCountCandidatures(DemandeVO demande) {
        return candidatureDao.findByDemandeIdCount(demande.getId());
    }
}
