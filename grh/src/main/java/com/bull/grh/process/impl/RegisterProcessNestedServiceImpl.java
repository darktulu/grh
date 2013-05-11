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

import com.bull.grh.domaine.Candidat;
import com.bull.grh.domaine.types.EmailType;
import com.bull.grh.process.RegisterProcessNestedService;
import com.bull.grh.repos.metier.CandidatDao;
import com.bull.grh.service.utils.EmailPreparatorService;
import com.bull.grh.view.admin.vo.EmailDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterProcessNestedServiceImpl implements RegisterProcessNestedService {
    private static Log log = LogFactory.getLog(RegisterProcessNestedServiceImpl.class);

    @Autowired
    private CandidatDao candidatDao;
    @Autowired
    private EmailPreparatorService emailService;

    @Override
    public void deleteAccount(String username) {
        try {
            Candidat user = candidatDao.findOne(username);
            candidatDao.delete(user);
        } catch (Exception e) {
            log.debug("No user with ID " + username + " Found");
        }
    }

    @Override
    public void sendRelanceMail(String username, String link, EmailDTO email) {
        Candidat user = candidatDao.findOne(username);
        if (user != null) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("lastname", user.getPrenom());
            params.put("firstname", user.getNom());
            params.put("link", link);
            try {
                email = emailService.populateEmail(EmailType.RELANCE, user.getEmail(), email.getFrom(), params, "", "");
                emailService.prepare(email);
            } catch (Exception e) {
                log.error("Error sending email relance for user " + username);
            }
        } else {
            log.debug("No user with ID " + username + " Found");
        }
    }

    @Override
    public void sendActivationMail(String username, EmailDTO email) {
        Candidat user = candidatDao.findOne(username);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lastname", user.getPrenom());
        params.put("firstname", user.getNom());
        params.put("login", user.getUsername());

        try {
            email = emailService.populateEmail(EmailType.ACTIVATION, user.getEmail(), email.getFrom(), params, "", "");
            emailService.prepare(email);
        } catch (Exception e) {
            log.error("Error sending email relance for user " + username);
        }
    }

    @Override
    public void sendActivationMailAgain(EmailDTO email) {
        try {
            emailService.prepare(email);
        } catch (Exception e) {
            log.error("Error sending email activation again");
        }
    }
}
