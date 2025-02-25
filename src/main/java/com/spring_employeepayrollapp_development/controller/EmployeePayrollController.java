package com.spring_employeepayrollapp_development.controller;

import com.spring_employeepayrollapp_development.model.EmployeeModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeePayrollController {
    private List<EmployeeModel> employeeList = new ArrayList<>();

    // GET all employees
    @GetMapping
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable int id) {
        if (id >= 0 && id < employeeList.size()) {
            return new ResponseEntity<>(employeeList.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST - Create new employee
    @PostMapping
    public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeData) {
        employeeList.add(employeeData);
        return new ResponseEntity<>(employeeData, HttpStatus.CREATED);
    }

    // PUT - Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable int id, @RequestBody EmployeeModel employeeData) {
        if (id >= 0 && id < employeeList.size()) {
            employeeList.set(id, employeeData);
            return new ResponseEntity<>(employeeData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        if (id >= 0 && id < employeeList.size()) {
            employeeList.remove(id);
            return new ResponseEntity<>("ID "+id+" Deleted!",(HttpStatus.OK));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}