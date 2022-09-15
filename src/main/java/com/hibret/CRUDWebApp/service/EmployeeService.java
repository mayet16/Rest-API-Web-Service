package com.hibret.CRUDWebApp.service;

import java.util.List;
import java.util.Optional;

import com.hibret.CRUDWebApp.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> saveEmployees(List<Employee> employees);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Optional<Employee> getEmployeeByFirstName(String name);
    Optional<Employee> getEmployeeByEmailId(String email);
    Employee updateEmployee(Employee updatedEmployee);
    void deleteEmployee(long id);
}