package com.spring_employeepayrollapp_development.controller;

import com.spring_employeepayrollapp_development.model.EmployeeModel;
import com.spring_employeepayrollapp_development.service.EmployeePayrollService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    @GetMapping
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        log.info("Fetching all employees");
        return new ResponseEntity<>(employeePayrollService.getAllEmployees(), HttpStatus.OK);
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable int id) {
        log.info("Fetching employee with ID: {}", id);
        EmployeeModel employee = employeePayrollService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            log.warn("Employee with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST - Create new employee
    @PostMapping
    public ResponseEntity<EmployeeModel> createEmployee(@Valid @RequestBody EmployeeModel employeeData) {
        log.info("Creating new employee: {}", employeeData);
        return new ResponseEntity<>(employeePayrollService.createEmployee(employeeData), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeModel employeeData, BindingResult result) {
        log.info("Updating employee with ID: {}", id);
        if (result.hasErrors()) {
            log.warn("Validation errors: {}", result.getAllErrors());
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        EmployeeModel updatedEmployee = employeePayrollService.updateEmployee(id, employeeData);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            log.warn("Employee with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        log.info("Deleting employee with ID: {}", id);
        if (employeePayrollService.deleteEmployee(id)) {
            return new ResponseEntity<>("ID " + id + " Deleted!", HttpStatus.OK);
        } else {
            log.warn("Employee with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}