package com.spring_employeepayrollapp_development.service;


import com.spring_employeepayrollapp_development.dto.EmployeeDTO;
import com.spring_employeepayrollapp_development.model.EmployeeModel;
import com.spring_employeepayrollapp_development.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private  List<EmployeeModel> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private ModelMapper modelMapper = new ModelMapper();

    public List<EmployeeDTO> getAllEmployees() {
        employeeList = employeeRepository.findAll();
        if (!employeeList.isEmpty()) {
            List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
            log.info("GET ALL EMPLOYEES");

            for(EmployeeModel employeeModel: employeeList){
                employeeDTOList.add(modelMapper.map(employeeModel,EmployeeDTO.class));
            }
            return employeeDTOList;
        } else {
            log.warn("NO EMPLOYEES FOUND");
            return null;
        }
    }

    public EmployeeDTO getEmployeeById(Long id) {
        log.info("GET EMPLOYEE BY ID");
        Optional<EmployeeModel> employeeEntity = employeeRepository.findById(id);
        if (employeeEntity.isPresent()) {
            return modelMapper.map(employeeEntity.get(), EmployeeDTO.class);
        } else {
            log.warn("Employee with ID: {} not found", id);
            return null;
        }
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        EmployeeModel newEmployee = modelMapper.map(employeeDTO, EmployeeModel.class);
        EmployeeModel savedEmployee = employeeRepository.save(newEmployee);
        log.info("ADDED EMPLOYEE");
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(long id, EmployeeDTO employeeDTO) {
        Optional<EmployeeModel> existingEmployeeOpt = employeeRepository.findById(id);
        if (existingEmployeeOpt.isPresent()) {
            EmployeeModel existingEmployee = existingEmployeeOpt.get();
            modelMapper.map(employeeDTO, existingEmployee);
            EmployeeModel updatedEmployee = employeeRepository.save(existingEmployee);
            log.info("UPDATED EMPLOYEE");
            return modelMapper.map(updatedEmployee, EmployeeDTO.class);
        }
        log.warn("EMPLOYEE NOT EXISTING");
        return null;
    }

    public boolean deleteEmployee(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            log.info("DELETED EMPLOYEE");
            return true;
        }
        log.warn("EMPLOYEE NOT FOUND");
        return false;
    }
}