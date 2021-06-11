package com.java.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.mysql.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Modifying
	@Query(value="delete from schedule where employee_id=:name", nativeQuery = true)
	void deletebyEmployeeId(@Param("name")String employeeId);

}