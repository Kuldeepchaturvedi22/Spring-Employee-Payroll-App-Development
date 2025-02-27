package com.spring_employeepayrollapp_development.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeModel {
    @Id
    @NotNull
    private long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name must contain only letters and spaces")
    private String name;

    @DecimalMax(value = "9999.0")
    private double salary;
}
