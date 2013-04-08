package com.bull.grh.view.metier.vo;

import java.io.Serializable;

public class QuestionClasseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String question;
    private String reponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

}
