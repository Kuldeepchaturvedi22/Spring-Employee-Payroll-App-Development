package com.spring_employeepayrollapp_development.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeePayrollController {
    // GET all employees
    @GetMapping
    public ResponseEntity<String> getAllEmployees() {
        return new ResponseEntity<>("Get All Employees", HttpStatus.OK);
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>("Get Employee by ID: " + id, HttpStatus.OK);
    }

    // POST - Create new employee
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Object employeeData) {
        return new ResponseEntity<>("Create Employee", HttpStatus.CREATED);
    }

    // PUT - Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Object employeeData) {
        return new ResponseEntity<>("Update Employee with ID: " + id, HttpStatus.OK);
    }

    // DELETE - Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>("Delete Employee with ID: " + id, HttpStatus.OK);
    }
}