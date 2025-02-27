package com.spring_employeepayrollapp_development.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class EmployeeDTO {
    @Id
    @NotNull
    private long id;
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name must contain only letters and spaces")
    private String name;
    @DecimalMax(value = "999.0",message = "too long")
    private double salary;
}
