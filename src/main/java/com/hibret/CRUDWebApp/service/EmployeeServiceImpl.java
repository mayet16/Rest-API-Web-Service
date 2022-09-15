package com.hibret.CRUDWebApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibret.CRUDWebApp.exception.ResourceNotFoundException;
import com.hibret.CRUDWebApp.model.Employee;
import com.hibret.CRUDWebApp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	 
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }
    
    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {

        return employeeRepository.saveAll(employees);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> getEmployeeByEmailId(String email) {
        return employeeRepository.findByEmailId(email);
    }
    @Override
    public Optional<Employee> getEmployeeByFirstName(String name) {
        return employeeRepository.findByFirstName(name);
    }
    
    @Override
    public Employee updateEmployee(Employee updatedEmployee) {
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
