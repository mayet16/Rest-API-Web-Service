package com.hibret.CRUDWebApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibret.CRUDWebApp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmailId(String email);

	Optional<Employee> findByFirstName(String name);
}
