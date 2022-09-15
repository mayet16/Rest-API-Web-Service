package com.hibret.CRUDWebApp.employeeServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hibret.CRUDWebApp.model.Employee;
import com.hibret.CRUDWebApp.repository.EmployeeRepository;
import com.hibret.CRUDWebApp.service.EmployeeService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class EmployeeServiceTests {
    /**
     * Autowire in the service we want to test
     */
    @Autowired
    private EmployeeService service;

    /**
     * Create a mock implementation of the EmployeeRepository
     */
    @MockBean
    private EmployeeRepository repository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Setup our mock repository
        Employee Employee1 =  Employee.builder()
                .firstName("adonay")
                .lastName("eshetu")
                .emailId("ady@gmail.com")
                .build();
        doReturn(Optional.of(Employee1)).when(repository).findById(1l);

        // Execute the service call
        Optional<Employee> returnedEmployee = service.getEmployeeById(1l);

        // Assert the response
        Assertions.assertTrue(returnedEmployee.isPresent(), "Employee  found");
        Assertions.assertSame(returnedEmployee.get(), Employee1, "The Employee returned was the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(repository).findById(1l);

        // Execute the service call
        Optional<Employee> returnedEmployee = service.getEmployeeById(1l);

        // Assert the response
        Assertions.assertFalse(returnedEmployee.isPresent(), "Employee should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock repository
        Employee Employee1 =  Employee.builder()
                .firstName("adonay")
                .lastName("eshetu")
                .emailId("ady@gmail.com")
                .build();
        Employee Employee2 =  Employee.builder()
                .firstName("haile")
                .lastName("mekonen")
                .emailId("haile@gmail.com")
                .build();
        doReturn(Arrays.asList(Employee1, Employee2)).when(repository).findAll();

        // Execute the service call
        List<Employee> Employees = service.getAllEmployees();

        // Assert the response
        Assertions.assertEquals(2, Employees.size(), "findAll should return 2 Employees");
    }

    @Test
    @DisplayName("Test save Employee")
    void testSave() {
        // Setup our mock repository
        Employee Employee1 =  Employee.builder()
                .firstName("adonay")
                .lastName("eshetu")
                .emailId("ady@gmail.com")
                .build();
        doReturn(Employee1).when(repository).save(any());

        // Execute the service call
        Employee returnedEmployee = service.saveEmployee(Employee1);

        // Assert the response
        Assertions.assertNotNull(returnedEmployee, "The saved Employee should not be null");
    }
}