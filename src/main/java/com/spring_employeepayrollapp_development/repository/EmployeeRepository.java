package com.spring_employeepayrollapp_development.repository;

import com.spring_employeepayrollapp_development.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeModel,Long> {
}
