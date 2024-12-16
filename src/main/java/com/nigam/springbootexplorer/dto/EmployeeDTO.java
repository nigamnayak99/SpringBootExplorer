package com.nigam.springbootexplorer.dto;


import com.nigam.springbootexplorer.entity.Employee;
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

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .employeeId(employeeDTO.getEmployeeId())
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .hireDate(employeeDTO.getHireDate())
                .jobTitle(employeeDTO.getJobTitle())
                .salary(employeeDTO.getSalary())
                .departmentId(employeeDTO.getDepartmentId())
                .managerId(employeeDTO.getManagerId())
                .createdAt(employeeDTO.getCreatedAt())
                .updatedAt(employeeDTO.getUpdatedAt())
                .build();
    }
}

