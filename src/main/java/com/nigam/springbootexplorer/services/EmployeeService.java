package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO findById(int id);
    List<EmployeeDTO> findAll();
    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateExistingEmployee(EmployeeDTO employeeDTO);
}
