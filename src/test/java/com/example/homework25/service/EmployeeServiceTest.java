package com.example.homework25.service;

import com.example.homework25.exception.EmployeeAlreadyAddedException;
import com.example.homework25.exception.EmployeeNotFoundException;
import com.example.homework25.exception.EmployeeStorageIsFullException;
import com.example.homework25.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class EmployeeServiceTest {

    @AfterEach
    public void afterEach() {
        employeeService.findAll().forEach(employee -> employeeService.remove(employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getSalary()));
    }

    private final EmployeeService employeeService = new EmployeeService();

    private Employee addOneWithCheck(String name, String surname) {
        Employee expected = new Employee(name, surname, 1, 20000);
        int sizeBefore = employeeService.findAll().size();
        employeeService.add(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary());
        assertThat(employeeService.findAll())
                .isNotEmpty()
                .hasSize(sizeBefore + 1)
                .contains(expected);
        assertThat(employeeService.find(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary())).isEqualTo(expected);
        return expected;
    }

    private Employee addOneWithCheckAllParameters(String name, String surname, int department, double salary) {
        Employee expected = new Employee(name, surname, 1, 20000);
        int sizeBefore = employeeService.findAll().size();
        employeeService.add(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary());
        assertThat(employeeService.findAll())
                .isNotEmpty()
                .hasSize(sizeBefore + 1)
                .contains(expected);
        assertThat(employeeService.find(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary())).isEqualTo(expected);
        return expected;
    }

    private Employee addOneWithCheck() {
        return addOneWithCheck("Name", "Surname");
    }

    @Test
    public void addPositiveTest() {
        addOneWithCheck();
    }

    @Test
    public void addNegativeTest() {
        Employee employee = addOneWithCheck();
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getSalary()));
    }

    @Test
    public void addNegative2Test() {
        for (int i = 0; i < 5; i++) {
            addOneWithCheck("Name" +(char) ('a' + i), "Surname" +(char) ('a' + i));
        }
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Vasya", "Vasyukov", 1, 20000));
    }

    @Test
    public void findPositive() {
        Employee employee1 = addOneWithCheckAllParameters("Name", "Surname", 1, 123);
        Employee employee2 = addOneWithCheckAllParameters("Namea", "Surnamea", 2, 225);
        assertThat(employeeService.find(employee1.getFirstName(), employee1.getLastName(), employee1.getDepartmentId(), employee2.getSalary()))
                .isEqualTo(employee1);
        assertThat(employeeService.find(employee2.getFirstName(), employee2.getLastName(), employee2.getDepartmentId(), employee2.getSalary()))
                .isEqualTo(employee2);

    }

    @Test
    public void findNegative() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Aaa", "Aaaa", 1, 123));
        addOneWithCheckAllParameters("Namea", "Surnamea", 1, 123);
        addOneWithCheckAllParameters("Nameb", "Surnameb", 1, 123);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Aaa", "Aaaa", 1, 123));

    }

    @Test
    public void removePositive() {
        Employee employee1 = addOneWithCheckAllParameters("Name", "Surname", 1, 123);
        Employee employee2 = addOneWithCheckAllParameters("Namea", "Surnamea", 2, 225);
        employeeService.remove(employee1.getFirstName(), employee1.getLastName(), employee1.getDepartmentId(), employee1.getSalary());
        assertThat(employeeService.findAll())
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(employee2);
        employeeService.remove(employee2.getFirstName(), employee2.getLastName(), employee2.getDepartmentId(), employee2.getSalary());
        assertThat(employeeService.findAll()).isEmpty();
    }

    @Test
    public void removeNegative() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("Aaa", "Aaaa", 1, 123));
        addOneWithCheckAllParameters("Namea", "Surnamea", 1, 123);
        addOneWithCheckAllParameters("Nameb", "Surnameb", 1, 123);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("Aaa", "Aaaa", 1, 123));
    }

}
