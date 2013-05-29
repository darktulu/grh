package com.bull.grh.process.impl;

import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EmailType;
import com.bull.grh.repos.metier.CandidatDao;
import com.bull.grh.repos.metier.EntretienDao;
import com.bull.grh.service.utils.EmailPreparatorService;
import com.bull.grh.view.admin.vo.EmailDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Service
public class EntretienProcessNestedImpl {
    @Inject
    private CandidatDao candidatDao;
    @Inject
    private EmailPreparatorService emailService;
    @Inject
    private EntretienDao entretienDao;

    private static Log log = LogFactory.getLog(RegisterProcessNestedServiceImpl.class);
    @Value("${email.sender}")
    private String sender;

    public void sendRefusMail(Long entretienId) {
        Entretien entretien = entretienDao.findOne(entretienId);
        Candidat user = entretien.getCandidature().getCandidat();

        if (user != null) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("firstname", user.getPrenom());
            params.put("lastname", user.getNom());
            params.put("poste", entretien.getCandidature().getDemande().getIntitulePoste());
            try {
                EmailDTO email = emailService.populateEmail(EmailType.REJECTED_AFTER_APP, user.getEmail(), sender, params, "", "");
                emailService.prepare(email);
            } catch (Exception e) {
                log.error("Error sending email refus for entretien : " + entretienId);
                e.printStackTrace();
            }
        } else {
            log.debug("No Entretien or user Found");
        }
    }

    public void sendAcceptMail(Long entretienId) {
        Entretien entretien = entretienDao.findOne(entretienId);
        Candidat user = entretien.getCandidature().getCandidat();

        if (user != null) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("lastname", user.getNom());
            params.put("firstname", user.getPrenom());
            params.put("poste", entretien.getCandidature().getDemande().getIntitulePoste());
            try {
                EmailDTO email = emailService.populateEmail(EmailType.CALL_WORK, user.getEmail(), sender, params, "", "");
                emailService.prepare(email);
            } catch (Exception e) {
                log.error("Error sending email refus for entretien : " + entretienId);
                e.printStackTrace();
            }
        } else {
            log.debug("No Entretien or user Found");
        }
    }
}
