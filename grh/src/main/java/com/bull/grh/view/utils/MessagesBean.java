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

package com.bull.grh.view.utils;

import org.primefaces.context.RequestContext;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Scope("session")
public class MessagesBean implements MessageSourceAware {
    private static MessageSource messageSource;

    private String title;
    private String message;
    private MessageType type = MessageType.DEFAULT;
    private Boolean show = false;

    public void showMessage(MessageType type, String title, String message) {
        this.type = type;
        this.show = true;
        this.title = new MessageBuilder()
                .code(title)
                .build()
                .resolveMessage(messageSource, Locale.FRANCE)
                .getText();
        this.message = new MessageBuilder()
                .code(message)
                .build()
                .resolveMessage(messageSource, Locale.FRANCE)
                .getText();
        RequestContext.getCurrentInstance().update("messages");
    }

    public void showMessageNatif(MessageType type, String title, String message) {
        this.title = title;
        this.type = type;
        this.show = true;
        this.message = message;
        RequestContext.getCurrentInstance().update("messages");
    }

    public void showMessage(String message) {
        this.type = MessageType.DEFAULT;
        this.show = true;
        this.message = new MessageBuilder()
                .code(message)
                .build()
                .resolveMessage(messageSource, Locale.FRANCE)
                .getText();
        RequestContext.getCurrentInstance().update("messages");
    }

    public void reset() {
        this.show = false;
        this.type = MessageType.DEFAULT;
        this.message = "";
        this.title = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        MessagesBean.messageSource = messageSource;
    }

    public enum MessageType {
        INFO("alert-info"), ERROR("alert-error"), SUCCESS("alert-success"), DEFAULT("");
        private String value;

        private MessageType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
