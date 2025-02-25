package com.spring_employeepayrollapp_development.controller;

import com.spring_employeepayrollapp_development.model.EmployeeModel;
import com.spring_employeepayrollapp_development.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    // GET all employees
    @GetMapping
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return new ResponseEntity<>(employeePayrollService.getAllEmployees(), HttpStatus.OK);
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable int id) {
        EmployeeModel employee = employeePayrollService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST - Create new employee
    @PostMapping
    public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeData) {
        return new ResponseEntity<>(employeePayrollService.createEmployee(employeeData), HttpStatus.CREATED);
    }

    // PUT - Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable int id, @RequestBody EmployeeModel employeeData) {
        EmployeeModel updatedEmployee = employeePayrollService.updateEmployee(id, employeeData);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        if (employeePayrollService.deleteEmployee(id)) {
            return new ResponseEntity<>("ID " + id + " Deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}