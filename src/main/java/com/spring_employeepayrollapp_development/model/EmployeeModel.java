package com.spring_employeepayrollapp_development.model;

public class EmployeeModel {
    private int id;
    private String name;
    private double salary;

    public void setName (String name) {
        this.name = name;
    }
    public void setSalary (double salary) {
        this.salary = salary;
    }
    public void setId (int id) {
        this.id = id;
    }
    public String getName () {
        return name;
    }
    public double getSalary () {
        return salary;
    }
    public int getId () {
        return id;
    }
}
