package com.cognizant.HMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.HMS.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

	   Optional<User> findByEmail(String email);
	   boolean existsByEmail(String email);
}
