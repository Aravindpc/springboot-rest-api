package com.java.mysql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.java.mysql.model.Schedule;
import com.java.mysql.repository.ScheduleRepository;

@Service
public class ScheduleService {


	    @Autowired
	    private ScheduleRepository scheduleRepository;

        public Schedule saveSchedule(Schedule schedule) {
			return scheduleRepository.saveAndFlush(schedule);
		}
	    public List<Schedule> findAll() {
	        return scheduleRepository.findAll();
	    }

	    public Schedule findOne(Long id) {
	        return scheduleRepository.findById(id).get();
	    }
	     
	    public boolean deleteSchedule(Long id) {
			scheduleRepository.deleteById(id);
			return true;
		}

}
