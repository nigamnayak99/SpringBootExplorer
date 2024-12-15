package com.nigam.springbootexplorer.dto;


import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String jobTitle;
    private BigDecimal salary;
    private Integer departmentId;
    private Integer managerId;
    private LocalDateTime createdAt;   // Added createdAt field
    private LocalDateTime updatedAt;   // Added updatedAt field

}

