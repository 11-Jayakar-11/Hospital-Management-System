package com.cognizant.HMS.contoller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.HMS.dto.AppointmentResponseDTO;
import com.cognizant.HMS.dto.AppointmentDTO;
import com.cognizant.HMS.dto.DoctorDTO;
import com.cognizant.HMS.entity.Appointment;
import com.cognizant.HMS.entity.Department;
import com.cognizant.HMS.entity.Doctor;
import com.cognizant.HMS.entity.Patient;
import com.cognizant.HMS.entity.User;
import com.cognizant.HMS.repository.PatientRepo;
import com.cognizant.HMS.repository.UserRepo;
import com.cognizant.HMS.service.AppointmentBookingService;

@RestController
@RequestMapping("/api/patient/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentBookingService service;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PatientRepo patientRepo;
	
	@GetMapping("/get-departments")
	public ResponseEntity<?> getDepartments(){
		List<Department> allDepartments = service.getAllDepartments();
		return ResponseEntity.ok(allDepartments);
	}
	
	@GetMapping("/get-doctors/{id}")
	public ResponseEntity<?> getDoctors(@PathVariable Long id){
		List<DoctorDTO> doctorsByDepartment = service.getDoctorsByDepartment(id);
		return ResponseEntity.ok(doctorsByDepartment);
	}
	
	@GetMapping("/available-slots")
	public ResponseEntity<?> getAvailableSlots(@RequestParam Long doctorId,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		List<LocalTime> availableSlots = service.getAvailableSlots(doctorId, date);
		return ResponseEntity.ok(availableSlots);
	}
	
	@PostMapping("/booking")
	public ResponseEntity<AppointmentResponseDTO> appointmentBooking(@RequestBody AppointmentDTO appointment,@RequestParam Long patientId){
		Long doctorId = appointment.getDoctorId();
		LocalDate date = appointment.getDate();
		LocalTime startTime = appointment.getStartTime();
		String reason = appointment.getReason();
		
//		String email = principal.getName();
//		User user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
//		Patient patient = patientRepo.findByUser(user).orElseThrow(()->new RuntimeException());
//		Long patientId = patient.getPatientId();
		
		Appointment bookedAppointment = service.appointmentBooking(patientId, doctorId, date, startTime, reason);
		
		AppointmentResponseDTO response = new AppointmentResponseDTO();
		response.setAppointmentId(bookedAppointment.getAppointmentId());
		response.setDoctorId(bookedAppointment.getDoctor().getDoctorId());
		response.setPatientId(bookedAppointment.getPatient().getPatientId());
		response.setDate(bookedAppointment.getDate());
		response.setTime(bookedAppointment.getStartTime());
		response.setReason(bookedAppointment.getReason());
		
		return ResponseEntity.ok(response);
	}
}
