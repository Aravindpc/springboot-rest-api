package com.java.mysql.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee")
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String employeeid;
	
	@OneToMany(mappedBy="employee",cascade= {CascadeType.REMOVE,CascadeType.ALL})
	@JsonIgnoreProperties("employee")
	private List<Schedule> schedule= new ArrayList<Schedule>();
	
	public Employee(String employeeid) {
		super();
		this.employeeid = employeeid;
	}

	public Employee(String employeeid, List<Schedule> schedule) {
		super();
		this.employeeid = employeeid;
		this.schedule = schedule;
	}

	public Employee() {
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}


}
