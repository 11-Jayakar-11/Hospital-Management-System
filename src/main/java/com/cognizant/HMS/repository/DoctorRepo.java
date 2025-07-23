package com.cognizant.HMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.HMS.dto.DoctorDTO;
import com.cognizant.HMS.entity.Doctor;
import com.cognizant.HMS.entity.User;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long>{
	Optional<Doctor> findByUser(User user);

	List<DoctorDTO> findByDepartmentId(Long departmentId);
}
