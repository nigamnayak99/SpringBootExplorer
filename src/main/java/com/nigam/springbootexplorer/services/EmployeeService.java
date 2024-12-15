package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.dto.EmployeeDTO;

public interface EmployeeService {
    public EmployeeDTO findById(int id);
}
