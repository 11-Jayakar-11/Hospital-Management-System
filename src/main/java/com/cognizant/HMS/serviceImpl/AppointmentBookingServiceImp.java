package com.cognizant.HMS.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.HMS.dto.DoctorDTO;
import com.cognizant.HMS.entity.Appointment;
import com.cognizant.HMS.entity.Department;
import com.cognizant.HMS.entity.Doctor;
import com.cognizant.HMS.entity.Patient;
import com.cognizant.HMS.repository.AppointmentRepo;
import com.cognizant.HMS.repository.DepartmentRepo;
import com.cognizant.HMS.repository.DoctorRepo;
import com.cognizant.HMS.repository.PatientRepo;
import com.cognizant.HMS.service.AppointmentBookingService;

@Service
public class AppointmentBookingServiceImp implements AppointmentBookingService{
	
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	AppointmentRepo appointmentRepo;
	
	@Autowired
	PatientRepo patientRepo;

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepo.findAll();
	}

	@Override
	public List<DoctorDTO> getDoctorsByDepartment(Long departmentId) {
		return doctorRepo.findByDepartmentId(departmentId);
	}

	@Override
	public List<LocalTime> getAvailableSlots(Long doctorId, LocalDate date) {
		List<LocalTime> allSlots = List.of(
				LocalTime.of(10, 0),
				LocalTime.of(10,30),
				LocalTime.of(11,0),
				LocalTime.of(11,30),
				LocalTime.of(12,00),
				LocalTime.of(12,30),
				LocalTime.of(2,0),
				LocalTime.of(2,30),
				LocalTime.of(3,0),
				LocalTime.of(3,30),
				LocalTime.of(4,0),
				LocalTime.of(4,30)
				);
		
		List<Appointment> bookedAppointment = appointmentRepo.findByDoctorIdAndDate(doctorId, date);
		
		List<LocalTime> bookedSlots = bookedAppointment.stream()
														.map(Appointment::getStartTime)
														.toList();
		return allSlots.stream()
				.filter(slot->!bookedSlots.contains(slot))
				.toList();
	}

	@Override
	public Appointment appointmentBooking(Long patientId, Long doctorId, LocalDate date, LocalTime time, String reason) {
		Patient patient = patientRepo.findById(patientId)
				.orElseThrow(()->new RuntimeException("Patient Not Found"));
		Doctor doctor = doctorRepo.findById(doctorId)
				.orElseThrow(()->new RuntimeException("Doctor Not Found"));
		
		Appointment appointment = new Appointment();
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		appointment.setDate(date);
		appointment.setStartTime(time);
		appointment.setReason(reason);
		
		appointmentRepo.save(appointment);
		return appointment;
	}

}
