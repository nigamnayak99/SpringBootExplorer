package com.nigam.springbootexplorer.services.impl;

import com.nigam.springbootexplorer.dto.EmployeeDTO;
import com.nigam.springbootexplorer.entity.Employee;
import com.nigam.springbootexplorer.repository.EmployeeRepository;
import com.nigam.springbootexplorer.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO findById(int id) {
       Employee employee = employeeRepository.findByEmployeeId(id).orElse(new Employee());
       return Employee.toDTO(employee);
    }
}
