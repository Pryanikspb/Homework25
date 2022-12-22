package com.example.homework25.service;

import com.example.homework25.exception.EmployeeNotFoundException;
import com.example.homework25.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        when(employeeService.findAll()).thenReturn(
                Arrays.asList(
                        new Employee("Ivan", "Petrov", 2, 10001),
                        new Employee("Oleg", "Krot", 2, 10002),
                        new Employee("Mariya", "Kostrova", 2, 20003),
                        new Employee("Nikolai", "Harlamov", 3, 10004),
                        new Employee("Sergei", "Melden", 3, 10005)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryTestParams")
    public void employeeWithMaxSalaryTest(int department,
                                          Employee expected) {
        assertThat(departmentService.findEmployeeWithMaxSalaryFromDepartment(department)).isEqualTo(expected);

    }

    public static Stream<Arguments> employeeWithMaxSalaryTestParams() {
        return Stream.of(
                Arguments.of(2, new Employee("Mariya", "Kostrova", 2, 20003)),
                Arguments.of(3, new Employee("Sergei", "Melden", 3, 10005)
                )

        );
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryTestParams")
    public void employeeWithMinSalaryTest(int department,
                                          Employee expected) {
        assertThat(departmentService.findEmployeeWithMinSalaryFromDepartment(department)).isEqualTo(expected);

    }

    public static Stream<Arguments> employeeWithMinSalaryTestParams() {
        return Stream.of(
                Arguments.of(2, new Employee("Ivan", "Petrov", 2, 10001)),
                Arguments.of(3, new Employee("Nikolai", "Harlamov", 3, 10004))

        );
    }

    @Test
    public void employeeWithMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> departmentService.findEmployeeWithMinSalaryFromDepartment(1));
    }

    @ParameterizedTest
    @MethodSource("findEmployeesFromDepartmentTestParams")
    public void findEmployeesFromDepartmentTest(int department,
                                          Collection <Employee> expected) {
        assertThat(departmentService.findEmployeesFromDepartment(department)).containsExactlyInAnyOrderElementsOf(expected);

    }

    public static Stream<Arguments> findEmployeesFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, Collections.emptyList()),
                Arguments.of(2,
                        List.of(
                                new Employee("Ivan", "Petrov", 2, 10001),
                                new Employee("Oleg", "Krot", 2, 10002),
                                new Employee("Mariya", "Kostrova", 2, 20003)
                        )
                ),
                Arguments.of(3,
                        List.of(
                                new Employee("Nikolai", "Harlamov", 3, 10004),
                                new Employee("Sergei", "Melden", 3, 10005)
                        )
                )

        );
    }

    @Test
    public void employeesFromDepartmentTest() {
        assertThat(departmentService.findEmployees()).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        2, List.of(new Employee("Ivan", "Petrov", 2, 10001),
                                new Employee("Oleg", "Krot", 2, 10002),
                                new Employee("Mariya", "Kostrova", 2, 20003)
                        ),
                        3, List.of(
                                new Employee("Nikolai", "Harlamov", 3, 10004),
                                new Employee("Sergei", "Melden", 3, 10005)
                        )
                )
        );

    }
}