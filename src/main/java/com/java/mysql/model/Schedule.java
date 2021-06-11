package com.java.mysql.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "scheduleid", nullable = false)
	private Long scheduleid;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	@JsonFormat(pattern="dd MMM yyyy")
    private Date startDate;
 
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	@JsonFormat(pattern="dd MMM yyyy")
	private Date endDate;
 
    @Column(name="time")
    @JsonFormat(pattern="HH:mm")
    private LocalTime time;

    @Column(name="duration")
    private int duration;
    
    @Column(name="isrepeated",columnDefinition = "boolean")
    private boolean isrepeated;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="frequency")
    private Frequency frequency;
    
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "employee_id")
	@JsonIgnoreProperties("schedule")
	private Employee employee;
	
	public Schedule(Long scheduleid, Date startDate, Date endDate, LocalTime time, int duration, boolean isrepeated,
			Frequency frequency, Employee employee) {
		super();
		this.scheduleid = scheduleid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.duration = duration;
		this.isrepeated = isrepeated;
		this.frequency = frequency;
		this.employee = employee;
	}	

	public Long getScheduleid() {
		return scheduleid;
	}


	public void setScheduleid(Long scheduleid) {
		this.scheduleid = scheduleid;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public boolean isIsrepeated() {
		return isrepeated;
	}


	public void setIsrepeated(boolean isrepeated) {
		this.isrepeated = isrepeated;
	}


	public Frequency getFrequency() {
		return frequency;
	}


	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}


	public Schedule() {
	}
	
	public  Employee getEmployee() {
		return employee;
	}

	public void setEmployee( Employee employee) {
		this.employee = employee;
	}

}
