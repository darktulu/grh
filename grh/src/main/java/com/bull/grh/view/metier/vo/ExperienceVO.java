package com.bull.grh.view.metier.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ExperienceVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank
    private String company;
    @NotNull
    private Date entrydate;
    private Date leavingdate;
    @NotBlank
    private String function;
    @NotBlank
    @Length(max = 450)
    private String description;
    private Double salary;
    private String departure;
    private CandidatVO candidat;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCompany() {
	return company;
    }

    public void setCompany(String company) {
	this.company = company;
    }

    public Date getEntrydate() {
	return entrydate;
    }

    public void setEntrydate(Date entrydate) {
	this.entrydate = entrydate;
    }

    public Date getLeavingdate() {
	return leavingdate;
    }

    public void setLeavingdate(Date leavingdate) {
	this.leavingdate = leavingdate;
    }

    public String getFunction() {
	return function;
    }

    public void setFunction(String function) {
	this.function = function;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Double getSalary() {
	return salary;
    }

    public void setSalary(Double salary) {
	this.salary = salary;
    }

    public String getDeparture() {
	return departure;
    }

    public void setDeparture(String departure) {
	this.departure = departure;
    }

    public CandidatVO getCandidat() {
	return candidat;
    }

    public void setCandidat(CandidatVO candidat) {
	this.candidat = candidat;
    }

}
