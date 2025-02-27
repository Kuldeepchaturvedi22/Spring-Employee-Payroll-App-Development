package com.spring_employeepayrollapp_development.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString

public class EmployeeDTO {
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name must contain only letters and spaces")
    public String name;
    @Min(value = 500,message = "Min wage should not be less then 500")
    public double salary;

    public String gender;

    public LocalDate startDate;

    public String note;

    public String profilePic;

    public List<String> department;
}
