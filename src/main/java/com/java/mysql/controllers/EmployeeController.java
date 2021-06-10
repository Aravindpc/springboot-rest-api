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

import com.java.mysql.model.Employee;
import com.java.mysql.model.Schedule;
import com.java.mysql.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value="/getall")
	public List<Employee> getAllEmployees()
	{
		return employeeService.findAll();
	}
	
	@GetMapping(value="/get/{employeeid}")
	public Optional<Employee> getEmployeeById(@PathVariable(value="employeeid")String employeeId)
	{
		return employeeService.findOne(employeeId);		
	}
	
	@PostMapping("/save")
	private Employee saveEmployee(@RequestBody Employee employee)   
	{  
	  
	    return employeeService.saveEmployee(employee);  
	} 
	
	@PutMapping("/update/{employeeid}")  
	private Employee update(@RequestBody Employee employee,@PathVariable(value="employeeid")String employeeId)   
	{  
		Employee existingEmployee=employeeService.findOne(employeeId).orElseThrow(EntityNotFoundException::new);
		List<Schedule> schedules=employee.getSchedule();
		for(int s=0;s<schedules.size();s++)
		{
			employeeService.updateSchedule(schedules.get(s));			
		}
		return employeeService.saveEmployee(existingEmployee);
	}  
	
	@DeleteMapping(value="/delete/{employeeid}")
	public boolean deleteEmployeeAndSchedule(@PathVariable(value="employeeid")String employeeId)
	{
		return employeeService.deleteEmployee(employeeId);
	}
	
	@DeleteMapping(value="/cancelschedules/{employeeid}")
	public boolean cancelScheduleofEmployee(@PathVariable(value="employeeid")String employeeId)
	{
		return employeeService.cancelScheduleofEmployee(employeeId);
	}
	
}
