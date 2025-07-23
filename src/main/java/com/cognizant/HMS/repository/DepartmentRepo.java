package com.cognizant.HMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.HMS.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long>{
	Department findByName(String deptname);
}
