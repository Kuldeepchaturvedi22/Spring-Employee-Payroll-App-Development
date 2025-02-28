package com.spring_employeepayrollapp_development.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "employee_payroll")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private double salary;
    @Column(name = "gender")
    private String gender;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "note")
    private String note;
    @Column(name = "profile_pic")
    private String profilePic;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> department;
}