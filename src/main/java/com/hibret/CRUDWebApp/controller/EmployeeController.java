package com.hibret.CRUDWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hibret.CRUDWebApp.exception.ResourceNotFoundException;
import com.hibret.CRUDWebApp.model.Employee;
import com.hibret.CRUDWebApp.repository.EmployeeRepository;
import com.hibret.CRUDWebApp.service.EmployeeService;

import java.util.List;

@CrossOrigin("*")
@RestController

//@RequestMapping("/api/employees") 
public class EmployeeController {

	@Autowired(required = true)
	private EmployeeService employeeService;
	/*
	 * public EmployeeController(EmployeeService employeeService) {
	 * this.employeeService = employeeService; }
	 */

	@PostMapping("/api/employees")
	@ResponseStatus(HttpStatus.CREATED) 
	public Employee createEmployee(@RequestBody Employee employee)
	{ 
		return employeeService.saveEmployee(employee); }
	
	@PostMapping("/api/all-employees")
	@ResponseStatus(HttpStatus.CREATED) 
	public List<Employee> createEmployees(@RequestBody List<Employee> employees)
	{ 
		return employeeService.saveEmployees(employees); }

	@GetMapping("/api/employees")
	public List<Employee> getAllEmployees()
	{ 
		return employeeService.getAllEmployees();
	}

	@GetMapping("/api/employees/{id}") 
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId)
	{
		return employeeService.getEmployeeById(employeeId) .map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build()); 
	}
	
	@GetMapping("/api/employeeByName/{firstName}") 
	public ResponseEntity<Employee> getEmployeeByFirstName(@PathVariable("firstName") String fname)
	{
		return employeeService.getEmployeeByFirstName(fname) .map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build()); 
	}
	
	@GetMapping("/api/employeeByEmail/{emailId}") 
	public ResponseEntity<Employee> getEmployeeByEmailId(@PathVariable("emailId") String email)
	{
		return employeeService.getEmployeeByEmailId(email) .map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build()); 
	}

	@PutMapping("/api/employees/{id}") 
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,@RequestBody Employee employee)
	{ 
		return employeeService.getEmployeeById(employeeId) .map(savedEmployee -> {

			savedEmployee.setFirstName(employee.getFirstName());
			savedEmployee.setLastName(employee.getLastName());
			savedEmployee.setEmailId(employee.getEmailId());

			Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
			return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

		}) .orElseGet(() -> ResponseEntity.notFound().build()); }

	@DeleteMapping("/api/employees/{id}")
	public ResponseEntity<String>deleteEmployee(@PathVariable("id") long employeeId){

		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<String>("Employee deleted successfully!.",
				HttpStatus.OK);

	}

}

/*
 * @CrossOrigin("*")
 * 
 * @RestController
 * 
 * @RequestMapping("/api/employees") public class EmployeeController {
 * 
 * @Autowired private EmployeeRepository employeeRepository;
 * 
 * @GetMapping public List<Employee> getAllEmployees(){ return
 * employeeRepository.findAll(); }
 * 
 * // build create employee REST API
 * 
 * @PostMapping public Employee createEmployee(@RequestBody Employee employee) {
 * return employeeRepository.save(employee); }
 * 
 * // build get employee by id REST API
 * 
 * @GetMapping("{id}") public ResponseEntity<Employee>
 * getEmployeeById(@PathVariable long id){ Employee employee =
 * employeeRepository.findById(id) .orElseThrow(() -> new
 * ResourceNotFoundException("Employee not exist with id:" + id)); return
 * ResponseEntity.ok(employee); }
 * 
 * // build update employee REST API
 * 
 * @PutMapping("{id}") public ResponseEntity<Employee>
 * updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
 * Employee updateEmployee = employeeRepository.findById(id) .orElseThrow(() ->
 * new ResourceNotFoundException("Employee not exist with id: " + id));
 * 
 * updateEmployee.setFirstName(employeeDetails.getFirstName());
 * updateEmployee.setLastName(employeeDetails.getLastName());
 * updateEmployee.setEmailId(employeeDetails.getEmailId());
 * 
 * employeeRepository.save(updateEmployee);
 * 
 * return ResponseEntity.ok(updateEmployee); }
 * 
 * // build delete employee REST API
 * 
 * @DeleteMapping("{id}") public ResponseEntity<HttpStatus>
 * deleteEmployee(@PathVariable long id){
 * 
 * Employee employee = employeeRepository.findById(id) .orElseThrow(() -> new
 * ResourceNotFoundException("Employee not exist with id: " + id));
 * 
 * employeeRepository.delete(employee);
 * 
 * return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 * 
 * } }
 */

