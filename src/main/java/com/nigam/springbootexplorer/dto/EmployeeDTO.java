package com.nigam.springbootexplorer.dto;


import com.nigam.springbootexplorer.annotations.JobTitleValidation;
import com.nigam.springbootexplorer.entity.Employee;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    @NotBlank(message = "This is a required field: employeeId")
    private Integer employeeId;

    @NotBlank(message = "This is a required field: firstName")
    private String firstName;

    @NotBlank(message = "This is a required field: lastName")
    private String lastName;

    @Email
    private String email;

    @NotBlank(message = "This is a required field: phoneNumber")
    private String phoneNumber;

    @NotBlank
    @FutureOrPresent
    @PastOrPresent
    private LocalDate hireDate;

    @JobTitleValidation
    private String jobTitle;

    @Positive
    @NotBlank(message = "Salary Can't be Zero")
    @Max(value = 1000000)
    private BigDecimal salary;

    @Positive
    @NotBlank(message = "Department Can't be Null")
    private Integer departmentId;

    private Integer managerId;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;

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

