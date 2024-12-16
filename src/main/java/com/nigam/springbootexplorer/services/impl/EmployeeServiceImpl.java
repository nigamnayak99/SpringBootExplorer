package com.nigam.springbootexplorer.services.impl;

import com.nigam.springbootexplorer.dto.EmployeeDTO;
import com.nigam.springbootexplorer.entity.Employee;
import com.nigam.springbootexplorer.repository.EmployeeRepository;
import com.nigam.springbootexplorer.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO findById(int id) {
       Employee employee = employeeRepository.findByEmployeeId(id).orElse(new Employee());
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
}
