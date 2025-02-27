package com.spring_employeepayrollapp_development.service;

import com.spring_employeepayrollapp_development.exception.EmployeeNotFoundException;
import com.spring_employeepayrollapp_development.model.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePayrollService {
    private List<EmployeeModel> employeeList = new ArrayList<>();

    public List<EmployeeModel> getAllEmployees() {
        return employeeList;
    }

    public EmployeeModel getEmployeeById(int id) {
        if (id >= 0 && id < employeeList.size()) {
            return employeeList.get(id);
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }

    public EmployeeModel createEmployee(EmployeeModel employeeData) {
        employeeList.add(employeeData);
        return employeeData;
    }

    public EmployeeModel updateEmployee(int id, EmployeeModel employeeData) {
        if (id >= 0 && id < employeeList.size()) {
            employeeList.set(id, employeeData);
            return employeeData;
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }

    public boolean deleteEmployee(int id) {
        if (id >= 0 && id < employeeList.size()) {
            employeeList.remove(id);
            return true;
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }
}