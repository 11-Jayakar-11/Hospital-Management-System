package com.cognizant.HMS.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.cognizant.HMS.dto.DoctorDTO;
import com.cognizant.HMS.entity.Appointment;
import com.cognizant.HMS.entity.Department;

public interface AppointmentBookingService {
	List<Department> getAllDepartments();
	List<DoctorDTO> getDoctorsByDepartment(Long DepartmentId);
	List<LocalTime> getAvailableSlots(Long doctorId,LocalDate date);
	Appointment appointmentBooking(Long patientId,Long doctorId,LocalDate date,LocalTime time,String reason);
}
