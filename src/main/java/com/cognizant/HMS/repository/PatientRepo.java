package com.cognizant.HMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.HMS.entity.Patient;
import com.cognizant.HMS.entity.User;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long>{
	Optional<Patient> findByUser(User user) ;
}
