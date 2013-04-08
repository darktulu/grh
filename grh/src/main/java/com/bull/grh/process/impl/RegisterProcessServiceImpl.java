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
import com.bull.grh.process.RegisterProcessService;
import com.bull.grh.repos.metier.CandidatDao;
import com.bull.grh.service.utils.EmailPreparatorService;
import com.bull.grh.view.admin.vo.EmailDTO;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterProcessServiceImpl implements RegisterProcessService {
    @Autowired
    private CandidatDao userRepos;
    @Autowired
    private EmailPreparatorService emailService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Value("${email.sender}")
    private String sender;
    @Value("${activationURL}")
    private String link;

    @Override
    public void register(Candidat user) throws Exception {
        link = link + user.getCodeActivationCompte();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lastname", user.getNom());
        params.put("firstname", user.getPrenom());
        params.put("link", link);

        EmailDTO email = emailService.populateEmail(EmailType.REGISTRATION, user.getEmail(), sender, params, "", "");
        emailService.prepare(email);

        Map<String, Object> processParams = new HashMap<String, Object>();
        processParams.put("token", user.getCodeActivationCompte());
        processParams.put("email", email);
        processParams.put("link", link);
        processParams.put("username", user.getUsername());

        runtimeService.startProcessInstanceByKey("register", user.getUsername(), processParams);
    }

    @Override
    public void activateAccount(String token) throws Exception {
        Task task = taskService.createTaskQuery().taskAssignee(token).singleResult();
        if (task == null) throw new Exception();

        String username = (String) runtimeService.getVariable(task.getExecutionId(), "username");
        Candidat user = userRepos.findOne(username);
        user.setCompteActive(true);
        userRepos.save(user);

        taskService.complete(task.getId());
    }

    @Override
    public void mailNotReceived(Candidat user) throws Exception {
        Execution execution = runtimeService.createExecutionQuery()
                .signalEventSubscriptionName("mailNotReceived")
                .processInstanceBusinessKey(user.getUsername())
                .singleResult();

        if (execution == null) throw new Exception();
        runtimeService.signalEventReceived("mailNotReceived", execution.getId());
    }
}
