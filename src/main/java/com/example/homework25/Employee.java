package com.example.homework25;

public class Employee {
    private final int id;
    private String fullname;
    private int department;
    private double salary;
    private static int counter;

    public Employee(String fullname, int department, double salary) {
        if (department < 0 || department > 5) throw new IllegalArgumentException("Введено неверное значение. Выберите отдел от 1 до 5.");
        this.id = ++counter;
        this.fullname = fullname;
        this.department = department;
        this.salary = salary;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setDepartment(int department) {
        if (department < 0 || department > 5) throw new IllegalArgumentException("Введено неверное значение. Выберите отдел от 1 до 5.");
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }


    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}