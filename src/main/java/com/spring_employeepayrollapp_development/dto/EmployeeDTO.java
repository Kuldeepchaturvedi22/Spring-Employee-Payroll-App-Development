package com.spring_employeepayrollapp_development.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @Pattern(regexp = "\\b(male|female|others)\\b", message = "Gender should be male,female or others")
    public String gender;

    @JsonFormat(pattern="dd MM yyyy")
    public LocalDate startDate;

    @NotBlank
    public String note;

    @NotBlank
    public String profilePic;

    @NotBlank
    public List<String> department;
}
