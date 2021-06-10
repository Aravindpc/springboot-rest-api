package com.java.mysql.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.mysql.model.Employee;
import com.java.mysql.model.Schedule;
import com.java.mysql.repository.EmployeeRepository;
import com.java.mysql.repository.ScheduleRepository;

@Service
public class EmployeeService {


	    @Autowired
	    private EmployeeRepository employeeRepository;
	    
	    @Autowired
	    private ScheduleRepository scheduleRepository;
	    
	    public Employee saveEmployee(Employee employee) {
	        return employeeRepository.saveAndFlush(employee);
	    }

	    public List<Employee> findAll() {
	        return employeeRepository.findAll();
	    }

	    public Optional<Employee> findOne(String id) {
	        return employeeRepository.findById(id);
	    }

	    public Employee insertEmployee(Employee employee)
	    {
	    	return employeeRepository.save(employee);
	    }
	    
	    public boolean deleteEmployee(String id) {
			employeeRepository.deleteById(id);
			return true;
		}
	    
	    @Transactional  
		public boolean cancelScheduleofEmployee(String employeeId) {
			scheduleRepository.deletebyEmployeeId(employeeId);
			return true;
		}

		public boolean updateSchedule(Schedule schedule) {
			Schedule presentSchedule=scheduleRepository.findById(schedule.getScheduleid()).orElseThrow(EntityNotFoundException::new);
			presentSchedule.setDuration(schedule.getDuration());
			presentSchedule.setEndDate(schedule.getEndDate());
			presentSchedule.setFrequency(schedule.getFrequency());
			presentSchedule.setIsrepeated(schedule.isIsrepeated());
			presentSchedule.setStartDate(schedule.getStartDate());
			presentSchedule.setTime(schedule.getTime());
			scheduleRepository.save(presentSchedule);
			return false;
		}
	    
}
