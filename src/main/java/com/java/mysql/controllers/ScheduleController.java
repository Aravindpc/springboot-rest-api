package com.java.mysql.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.mysql.model.Employee;
import com.java.mysql.model.Schedule;
import com.java.mysql.service.EmployeeService;
import com.java.mysql.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value="/getall")
	public List<Schedule> getAllSchedules()
	{
		return scheduleService.findAll();
	}
	
	@GetMapping(value="/get/{scheduleid}")
	public Schedule getSchedule(@PathVariable(value="scheduleid")Long scheduleId)
	{
		return scheduleService.findOne(scheduleId);
		
	}
	
	@PostMapping("/create")
	private Schedule saveSchedule(@RequestBody @Valid Schedule schedule)   
	{  
		Optional<Employee> emp=employeeService.findOne(schedule.getEmployee().getEmployeeid());
	    schedule.setEmployee(emp.get());
	    Schedule savedSchedule=scheduleService.saveSchedule(schedule);
	    
		return scheduleService.saveSchedule(savedSchedule);  
	} 
	
	@PutMapping("/update/{scheduleid}")  
	private Schedule update(@RequestBody @Valid Schedule schedule,@PathVariable(value="scheduleid")Long scheduleId)   
	{  
		Optional<Employee> emp=employeeService.findOne(schedule.getEmployee().getEmployeeid());
		Schedule existingSchedule=scheduleService.findOne(scheduleId);
		existingSchedule.setScheduleid(scheduleId);
		existingSchedule.setDuration(schedule.getDuration());
		existingSchedule.setEndDate(schedule.getEndDate());
		existingSchedule.setStartDate(schedule.getStartDate());
		existingSchedule.setFrequency(schedule.getFrequency());
		existingSchedule.setTime(schedule.getTime());
		existingSchedule.setDuration(schedule.getDuration());
		existingSchedule.setIsrepeated(schedule.isIsrepeated());
		existingSchedule.setEmployee(emp.get());
		return scheduleService.saveSchedule(existingSchedule);
	}  
	@DeleteMapping(value="/delete/{scheduleid}")
	public boolean deleteSchedule(@PathVariable(value="scheduleid")Long scheduleId)
	{
		return scheduleService.deleteSchedule(scheduleId);
	}
}
