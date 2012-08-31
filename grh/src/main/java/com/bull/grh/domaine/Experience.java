package com.bull.grh.domaine;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String company;
    @Temporal(TemporalType.DATE)
    private Date entrydate;
    @Temporal(TemporalType.DATE)
    private Date leavingdate;
    private String function;
    private String description;
    private Double salary;
    private String departure;

    @ManyToOne
    private Candidat candidat;

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

    public Candidat getCandidat() {
	return candidat;
    }

    public void setCandidat(Candidat candidat) {
	this.candidat = candidat;
    }

}