package com.bull.grh.service.metier.impl;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.Candidature;
import com.bull.grh.domaine.Demande;
import com.bull.grh.domaine.DossierCandidature;
import com.bull.grh.domaine.types.EtatDemande;
import com.bull.grh.domaine.types.ProcessConst;
import com.bull.grh.process.JbpmDemandeService;
import com.bull.grh.repos.metier.CandidatureDao;
import com.bull.grh.repos.metier.DemandeDao;
import com.bull.grh.service.exception.AlreadyHaveCandidatureException;
import com.bull.grh.service.exception.CandidatureNotFoundException;
import com.bull.grh.service.exception.DemandeHaveNoCandidatureException;
import com.bull.grh.service.metier.DemandeService;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.CandidatureVO;
import com.bull.grh.view.metier.vo.DemandeVO;
import org.dozer.DozerBeanMapper;
import org.jbpm.task.Task;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("demandeService")
public class DemandeServiceImpl implements DemandeService {

    @Inject
    private JbpmDemandeService jbpmDemandeService;
    @Inject
    private CandidatureDao candidatureDao;
    @Inject
    private DozerBeanMapper mapper;
    @Inject
    private DemandeDao demandeDao;

    @Override
    public void createDemande(DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.NEW);
        demande.getPersonne().setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        demande = mapper.map(demandeDao.save(mapper.map(demande, Demande.class)), DemandeVO.class);
        jbpmDemandeService.startProcess("ROLE_OP", demande);
    }

    @Override
    public void startTaskDemandeRH(Task task, DemandeVO demande) {
        jbpmDemandeService.startTask("ROLE_RH", task);
    }

    @Override
    public void startTaskDemandeOP(Task task, DemandeVO demande) {
        jbpmDemandeService.startTask(SecurityContextHolder.getContext().getAuthentication().getName(), task);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<DemandeVO, Task> loadDemandesNouveau() {
        DemandeVO demandeVO = new DemandeVO();
        List<Task> tasks = new ArrayList<Task>();
        Map<DemandeVO, Task> map = new HashMap<DemandeVO, Task>();
        tasks = jbpmDemandeService.getTaskList(ProcessConst.DEMANDE_TASK_OP_INIT, SecurityContextHolder.getContext()
                .getAuthentication().getName());
        for (Task task : tasks) {
            demandeVO = getDemandeFromTask(task);
            map.put(demandeVO, task);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<DemandeVO, Task> loadDemandesTraite() {
        DemandeVO demandeVO = new DemandeVO();
        List<Task> tasks = new ArrayList<Task>();
        Map<DemandeVO, Task> map = new HashMap<DemandeVO, Task>();
        tasks = jbpmDemandeService.getTaskList(ProcessConst.DEMANDE_TASK_OP_CHOICE, SecurityContextHolder.getContext()
                .getAuthentication().getName());
        for (Task task : tasks) {
            demandeVO = getDemandeFromTask(task);
            map.put(demandeVO, task);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<DemandeVO, Task> loadDemandesSoumise() {
        DemandeVO demandeVO = new DemandeVO();
        List<Task> tasks = new ArrayList<Task>();
        Map<DemandeVO, Task> map = new HashMap<DemandeVO, Task>();
        tasks = jbpmDemandeService.getTaskList(ProcessConst.DEMANDE_TASK_RH_VALIDATION, "ROLE_RH");
        for (Task task : tasks) {
            demandeVO = getDemandeFromTask(task);
            map.put(demandeVO, task);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<DemandeVO, Task> loadStartedDemandesTraite() {
        DemandeVO demandeVO = new DemandeVO();
        List<Task> tasks = new ArrayList<Task>();
        Map<DemandeVO, Task> map = new HashMap<DemandeVO, Task>();
        tasks = jbpmDemandeService.getStartedTaskList(ProcessConst.DEMANDE_TASK_OP_CHOICE, SecurityContextHolder
                .getContext().getAuthentication().getName());
        for (Task task : tasks) {
            demandeVO = getDemandeFromTask(task);
            map.put(demandeVO, task);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<DemandeVO, Task> loadStartedDemandesSoumise() {
        DemandeVO demandeVO = new DemandeVO();
        List<Task> tasks = new ArrayList<Task>();
        Map<DemandeVO, Task> map = new HashMap<DemandeVO, Task>();
        tasks = jbpmDemandeService.getStartedTaskList(ProcessConst.DEMANDE_TASK_RH_VALIDATION, "ROLE_RH");
        for (Task task : tasks) {
            demandeVO = getDemandeFromTask(task);
            map.put(demandeVO, task);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountDemandesTraite() {
        return jbpmDemandeService.getCountTaskList(ProcessConst.DEMANDE_TASK_OP_CHOICE, SecurityContextHolder
                .getContext().getAuthentication().getName());
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountDemandesSoumise() {
        return jbpmDemandeService.getCountTaskList(ProcessConst.DEMANDE_TASK_RH_VALIDATION, "ROLE_RH");
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountStartedDemandesTraite() {
        return jbpmDemandeService.getCountStartedTaskList(ProcessConst.DEMANDE_TASK_OP_CHOICE, SecurityContextHolder
                .getContext().getAuthentication().getName());
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountStartedDemandesSoumise() {
        return jbpmDemandeService.getCountStartedTaskList(ProcessConst.DEMANDE_TASK_RH_VALIDATION, "ROLE_RH");
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountDemandesNouveau() {
        return jbpmDemandeService.getCountTaskList(ProcessConst.DEMANDE_TASK_OP_INIT, SecurityContextHolder
                .getContext().getAuthentication().getName());
    }

    @Override
    public void sendDemande(Task task, DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.SENT);
        demandeDao.save(mapper.map(demande, Demande.class));

        // proceed by completing the task
        jbpmDemandeService.startAndCompleteTask(SecurityContextHolder.getContext().getAuthentication().getName(), task,
                demande);
    }

    @Override
    public void deleteDemande(Task task, DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.DELETED);
        demandeDao.save(mapper.map(demande, Demande.class));

        // proceed by completing the task
        jbpmDemandeService.startAndCompleteTask(SecurityContextHolder.getContext().getAuthentication().getName(), task,
                demande);
    }

    @Override
    public void rejectDemande(Task task, DemandeVO demande) {
        demande.setEtatDemande(EtatDemande.REJECTED);
        demandeDao.save(mapper.map(demande, Demande.class));

        // proceed by completing the task
        jbpmDemandeService.startAndCompleteTask("ROLE_RH", task, demande);
    }

    @Override
    public void rejectDemandeAfterAccepting(Task task, DemandeVO demande) {
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
        jbpmDemandeService.completeTask("ROLE_RH", task, demande);
    }

    @Override
    public void completeDemande(Task task, DemandeVO demande) throws DemandeHaveNoCandidatureException {

        List<Candidature> candidatures = candidatureDao.findByDemand(demande.getId());

        if (candidatures == null || candidatures.isEmpty())
            throw new DemandeHaveNoCandidatureException();

        // Change the status of the Demand to TRAITE and persist the
        // entity
        demande.setEtatDemande(EtatDemande.APPROVED);
        demandeDao.save(mapper.map(demande, Demande.class));

        // proceed by completing the task
        jbpmDemandeService.completeTask("ROLE_RH", task, demande);

    }

    @Override
    public void completeDemande(Task task, DemandeVO demande, List<CandidatureVO> candidatureList)
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
        jbpmDemandeService.completeTask(SecurityContextHolder.getContext().getAuthentication().getName(), task,
                demande, candidatureList);

    }

    @Override
    public void addCandidateToDemand(CandidatVO candidat, DemandeVO demand) throws AlreadyHaveCandidatureException {
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

    @Transactional(readOnly = true)
    private DemandeVO getDemandeFromTask(Task task) {
        return (DemandeVO) jbpmDemandeService.getTaskContent(task).get(ProcessConst.DEMANDE_DEMANDE);
    }
}
