package com.nigam.springbootexplorer.services.impl;

import com.nigam.springbootexplorer.dto.EmployeeDTO;
import com.nigam.springbootexplorer.entity.Employee;
import com.nigam.springbootexplorer.repository.EmployeeRepository;
import com.nigam.springbootexplorer.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO findById(int id) {
        Employee employee = employeeRepository.findByEmployeeId(id).orElseThrow(() -> new NoSuchElementException("Employee Was not found"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(Employee::toDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(EmployeeDTO.toEntity(employeeDTO));
        return Employee.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateExistingEmployee(EmployeeDTO employeeDTO) {
       Employee existingEmployee = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee Doesn't Exists !!"));
       Employee updatedEmployee = new Employee();
       BeanUtils.copyProperties(existingEmployee, updatedEmployee);
       employeeRepository.save(updatedEmployee);
       return modelMapper.map(updatedEmployee, EmployeeDTO.class);
    }

}
