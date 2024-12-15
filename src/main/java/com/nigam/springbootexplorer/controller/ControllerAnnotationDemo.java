package com.nigam.springbootexplorer.controller;

import com.nigam.springbootexplorer.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDate;


@Controller
//@RequestMapping(path = "/employee-controller")
public class ControllerAnnotationDemo {

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String getEmployeeById() {
        return "index";
    }
}
