package com.spring_employeepayrollapp_development.dto;

public class EmployeeDTO {
    private String name;
    private double salary;

    public void setName (String name) {
        this.name = name;
    }
    public void setSalary (double salary) {
        this.salary = salary;
    }
    public String getName () {
        return name;
    }
    public double getSalary () {
        return salary;
    }
}
