package com.spring_employeepayrollapp_development.controller;

import com.spring_employeepayrollapp_development.dto.EmployeeDTO;
import com.spring_employeepayrollapp_development.service.EmployeePayrollService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTO = employeePayrollService.getAllEmployees();
        log.info("Fetching all employees");
        if (employeeDTO != null) {
            log.info("Employees found: {}", employeeDTO.size());
            return ResponseEntity.ok(employeeDTO);
        } else {
            log.warn("No employees found");
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        EmployeeDTO employee = employeePayrollService.getEmployeeById(id);
        if (employee != null) {
            log.info("Employee found: {}", employee);
            return ResponseEntity.ok(employee);
        } else {
            log.warn("Employee with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employee) {
        log.info("Creating new employee: {}", employee);
        EmployeeDTO employeeDTO = employeePayrollService.addEmployee(employee);
        if (employeeDTO != null) {
            log.info("Employee created successfully: {}", employeeDTO);
            return ResponseEntity.ok(employeeDTO);
        } else {
            log.error("Failed to create employee");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO updatedEmployee) {
        log.info("Updating employee with ID: {}", id);
        EmployeeDTO employeeDTO = employeePayrollService.updateEmployee(id, updatedEmployee);
        if (employeeDTO != null) {
            log.info("Employee updated successfully: {}", employeeDTO);
            return ResponseEntity.ok(employeeDTO);
        } else {
            log.warn("Employee with ID: {} not found", id);
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "ID not FOUND");
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        if (employeePayrollService.deleteEmployee(id)) {
            log.info("Employee deleted successfully");
            return ResponseEntity.ok("Employee deleted successfully");
        } else {
            log.warn("Employee with ID: {} not found", id);
            return ResponseEntity.badRequest().body("Employee not found");
        }
    }
}