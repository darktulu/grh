package com.bull.grh.service.utils.impl;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bull.grh.domaine.EmailTemplate;
import com.bull.grh.domaine.types.DecisionEntretien;
import com.bull.grh.domaine.types.EmailCode;
import com.bull.grh.repos.admin.EmailTemplateDao;
import com.bull.grh.service.utils.EmailService;
import com.bull.grh.view.admin.vo.EmailVO;
import com.bull.grh.view.metier.vo.CandidatVO;
import com.bull.grh.view.metier.vo.EntretienVO;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    protected static Logger logger = Logger.getLogger("TemplateEmailService");

    @Inject
    private VelocityEngine velocityEngine;

    @Inject
    private DozerBeanMapper mapper;

    @Value("${activationURL}")
    private String link;

    @Inject
    private JavaMailSender mailSender;

    @Inject
    private EmailTemplateDao emailTemplateDao;

    @Override
    public EmailVO populateEmail(EmailCode code, String to, String from, Map<String, String> params, String cc,
	    String bcc) throws Exception {

	velocityEngine.init();
	VelocityContext context = new VelocityContext();

	Iterator<String> itValue = params.values().iterator();
	Iterator<String> itKey = params.keySet().iterator();

	while (itValue.hasNext()) {
	    context.put(itKey.next(), itValue.next());
	}

	EmailTemplate emailTemplate = emailTemplateDao.findByCode(code);
	String template = emailTemplate.getMessage();

	StringWriter contenuWriter = new StringWriter();
	velocityEngine.evaluate(context, contenuWriter, "", template);
	String contenuMessage = contenuWriter.toString();

	EmailVO emailVO = new EmailVO();
	emailVO.setSubject(emailTemplate.getSubject());
	emailVO.setFrom(from);
	emailVO.setTo(to);
	emailVO.setCc(cc);
	emailVO.setBcc(bcc);
	emailVO.setBody(contenuMessage);

	return emailVO;

    }

    public EmailVO emailToActivateAccount(CandidatVO candidatVO) {
	Map<String, String> params = new HashMap<String, String>();
	params.put("prenom", candidatVO.getPrenom());
	params.put("nom", candidatVO.getNom());
	params.put("link", link + candidatVO.getCodeActivationCompte());
	try {
	    return populateEmail(EmailCode.REGISTER_NEW_ACCOUNT, candidatVO.getEmail(), "recrut@bull.com", params, "",
		    "");
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public EmailVO emailReActivate(CandidatVO candidatVO) {
	Map<String, String> params = new HashMap<String, String>();
	params.put("prenom", candidatVO.getPrenom());
	params.put("nom", candidatVO.getNom());
	params.put("link", link + candidatVO.getCodeActivationCompte());

	try {
	    return populateEmail(EmailCode.REGISTER_RECALL, candidatVO.getEmail(), "recrut@bull.com", params, "", "");
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public EmailVO emailAccountActivated(CandidatVO candidatVO) {
	Map<String, String> params = new HashMap<String, String>();
	params.put("prenom", candidatVO.getPrenom());
	params.put("nom", candidatVO.getNom());
	params.put("login", candidatVO.getUsername());
	params.put("password", "********");

	try {
	    return populateEmail(EmailCode.REGISTER_COMPLETED, candidatVO.getEmail(), "recrut@bull.com", params, "", "");
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public EmailVO emailConvocation(EntretienVO entretien) {
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String, String> params = new HashMap<String, String>();

	params.put("prenom", entretien.getCandidature().getCandidat().getPrenom());
	params.put("nom", entretien.getCandidature().getCandidat().getNom());
	params.put("date", formatter.format(entretien.getDate()));
	params.put("heure", entretien.getHeure());
	params.put("today", formatter.format(new Date()));
	params.put("adresse", entretien.getAdresse());
	params.put("contact", entretien.getContactSurPlace());
	if (entretien.getCandidature().getDossierCandidature().isDossierComplet())
	    params.put("dossier", "");
	else
	    params.put("dossier", "Veulliez remplir votre dossier de candidature disponible sur votre page de profil !");
	try {
	    return populateEmail(EmailCode.CALL_APPOINTMENT, entretien.getCandidature().getCandidat().getEmail(),
		    "recrut@bull.com", params, "", "");
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    @Override
    public EmailVO emailEntretien(EntretienVO entretien) {
	Map<String, String> params = new HashMap<String, String>();

	params.put("prenom", entretien.getCandidature().getCandidat().getPrenom());
	params.put("nom", entretien.getCandidature().getCandidat().getNom());
	params.put("poste", entretien.getCandidature().getDemande().getIntitulePoste());
	try {
	    if (entretien.getDecisionEntretien() == DecisionEntretien.OK)
		return populateEmail(EmailCode.CALL_WORK, entretien.getCandidature().getCandidat().getEmail(),
			"recrut@bull.com", params, "", "");
	    else if (entretien.getDecisionEntretien() == DecisionEntretien.REJECTED)
		return populateEmail(EmailCode.REJECTED_AFTER_APP, entretien.getCandidature().getCandidat().getEmail(),
			"recrut@bull.com", params, "", "");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
}
