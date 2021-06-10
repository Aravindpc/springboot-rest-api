package com.java.mysql.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.mysql.model.Schedule;
import com.java.mysql.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	
	@GetMapping(value="/getall")
	public List<Schedule> getAllSchedules()
	{
		return scheduleService.findAll();
	}
	
	@GetMapping(value="/get/{scheduleid}")
	public Optional<Schedule> getSchedule(@PathVariable(value="scheduleid")Long scheduleId)
	{
		return scheduleService.findOne(scheduleId);
		
	}
	
	@PostMapping("/save")
	private Schedule saveSchedule(@RequestBody Schedule schedule)   
	{  
	    return scheduleService.saveSchedule(schedule);  
	} 
	
	@PutMapping("/update/{scheduleid}")  
	private Schedule update(@RequestBody Schedule schedule,@PathVariable(value="scheduleid")Long scheduleId)   
	{  
		Schedule existingSchedule=scheduleService.findOne(scheduleId).orElseThrow(EntityNotFoundException::new);
		existingSchedule.setScheduleid(scheduleId);
		existingSchedule.setDuration(schedule.getDuration());
		existingSchedule.setEndDate(schedule.getEndDate());
		existingSchedule.setStartDate(schedule.getStartDate());
		existingSchedule.setFrequency(schedule.getFrequency());
		existingSchedule.setTime(schedule.getTime());
		existingSchedule.setDuration(schedule.getDuration());
		existingSchedule.setIsrepeated(schedule.isIsrepeated());
		existingSchedule.setEmployee(schedule.getEmployee());
		return scheduleService.saveSchedule(existingSchedule);
	}  
	@DeleteMapping(value="/delete/{scheduleid}")
	public boolean deleteSchedule(@PathVariable(value="scheduleid")Long scheduleId)
	{
		return scheduleService.deleteSchedule(scheduleId);
	}
}
