package com.nigam.springbootexplorer.controller;

import com.nigam.springbootexplorer.dto.EmployeeDTO;
import com.nigam.springbootexplorer.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.findAll();
    }

    @PostMapping(path = "/create")
    public EmployeeDTO createEmployee( @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createNewEmployee(employeeDTO);
    }

    @PutMapping(path = "/update")
    public EmployeeDTO updateExistingEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateExistingEmployee(employeeDTO);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception) {
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
}
