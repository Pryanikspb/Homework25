package com.example.homework25.model;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;

    private final int department;

    private final double salary;

    public Employee(String firstName,
                    String lastName,
                    int department,
                    double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Фамилия Имя: %s %s, отдел: %d, ЗП: %.2f", firstName, lastName, department, salary);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartmentId() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(employee.salary, salary) ==0 && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }
}