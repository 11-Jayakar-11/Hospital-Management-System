package com.cognizant.HMS.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.HMS.entity.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long>{


	List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDate date);

}
