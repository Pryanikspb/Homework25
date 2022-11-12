package com.example.homework25.service;

import com.example.homework25.exception.EmployeeAlreadyAddedException;
import com.example.homework25.exception.EmployeeNotFoundException;
import com.example.homework25.exception.EmployeeStorageIsFullException;
import com.example.homework25.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final int SIZE = 5;

    private final List<Employee> employees;

    private final ValidatorService validatorService;


    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.employees = new ArrayList<>();
    }

    public Employee add(String firstName,
                        String lastName,
                        int department,
                        double salary) {
        Employee employee = new Employee(
                validatorService.validate(firstName),
                validatorService.validate(lastName),
                department,
                salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size()>= SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        employees.add(employee);
        return employee;
    }

    public Employee remove(String firstName,
                           String lastName,
                           int department,
                           double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    public Employee find(String firstName,
                         String lastName,
                         int department,
                         double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}
