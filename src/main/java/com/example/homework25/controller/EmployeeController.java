package com.example.homework25.controller;


import com.example.homework25.model.Employee;
import com.example.homework25.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam ("departmentId") int department,
                        @RequestParam double salary) {
        return employeeService.add(firstName, lastName, department,salary);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName,
                        @RequestParam String lastName,
                         @RequestParam ("departmentId") int department,
                         @RequestParam double salary) {
        return employeeService.find(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName,
                         @RequestParam String lastName,
                           @RequestParam ("departmentId") int department,
                           @RequestParam double salary) {
        return employeeService.remove(firstName, lastName, department, salary);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

}
