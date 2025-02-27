package com.spring_employeepayrollapp_development.controller;

import com.spring_employeepayrollapp_development.dto.EmployeeDTO;
import com.spring_employeepayrollapp_development.service.EmployeePayrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTO = employeePayrollService.getAllEmployees();
        if (employeeDTO!=null) {
            return ResponseEntity.ok(employeeDTO);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeePayrollService.getEmployeeById(id);
        if (employee!=null){
            return ResponseEntity.ok(employee);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employee) {
        EmployeeDTO employeeDTO = employeePayrollService.addEmployee(employee);
        if(employeeDTO!=null) {
            return ResponseEntity.ok(employeeDTO);
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDTO updatedEmployee) {
        EmployeeDTO employeeDTO = employeePayrollService.updateEmployee(id,updatedEmployee);
        if(employeeDTO!=null) return ResponseEntity.ok(employeePayrollService.updateEmployee(id,updatedEmployee));
        Map<String, String> errors = new HashMap<>();
        errors.put("error","ID not FOUND");
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (employeePayrollService.deleteEmployee(id)) {
            return ResponseEntity.ok("Employee deleted successfully");
        }
        return ResponseEntity.badRequest().body("Employee not found");
    }
}