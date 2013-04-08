/**
 * Copyright 2012 Nuvola Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.bull.grh.process.impl;

import com.bull.grh.domaine.Entretien;
import com.bull.grh.domaine.types.EmailType;
import com.bull.grh.repos.metier.EntretienDao;
import com.bull.grh.service.utils.EmailPreparatorService;
import com.bull.grh.view.admin.vo.EmailDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConvocationProcessNestedImpl {

    private static Log log = LogFactory.getLog(ConvocationProcessNestedImpl.class);

    @Inject
    private EntretienDao entretienDao;
    @Inject
    private EmailPreparatorService emailService;

    @Value("email.sender")
    private String sender;

    public void verify(Long entretienId) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Entretien entretien = entretienDao.findOne(entretienId);

        try {
            Map<String, Object> params = new HashMap<String, Object>();
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

            EmailDTO email = emailService.populateEmail(EmailType.CALL_APPOINTMENT,
                    entretien.getCandidature().getCandidat().getEmail(), sender, params, "", "");
            emailService.prepare(email);
        } catch (Exception e) {
            log.error("Error sending email for appointment ID : " + entretienId);
        }
    }
}
