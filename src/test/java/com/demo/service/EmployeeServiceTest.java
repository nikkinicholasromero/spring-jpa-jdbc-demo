package com.demo.service;

import com.demo.model.Employee;
import com.demo.repository.jdbc.EmployeeJdbcRepository;
import com.demo.repository.jpa.EmployeeJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService target;

    @Mock
    private EmployeeJpaRepository employeeJpaRepository;

    @Mock
    private EmployeeJdbcRepository employeeJdbcRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        Employee employee1 = new Employee();
        employee1.setId(1);

        Employee employee2 = new Employee();
        employee2.setId(2);

        Iterable<Employee> iterableEmployees1 = Arrays.asList(employee1, employee2);

        when(employeeJpaRepository.findAll()).thenReturn(iterableEmployees1);

        Employee employee3 = new Employee();
        employee3.setId(3);

        Employee employee4 = new Employee();
        employee4.setId(4);

        List<Employee> employeeList = Arrays.asList(employee3, employee4);

        when(employeeJdbcRepository.findAll()).thenReturn(employeeList);

        List<Employee> actual = target.findAll();

        assertThat(actual).isNotNull();
        assertThat(actual).isNotEmpty();
        assertThat(actual.size()).isEqualTo(4);
        assertThat(actual.get(0)).isNotNull();
        assertThat(actual.get(0).getId()).isEqualTo(1);
        assertThat(actual.get(1)).isNotNull();
        assertThat(actual.get(1).getId()).isEqualTo(2);
        assertThat(actual.get(2)).isNotNull();
        assertThat(actual.get(2).getId()).isEqualTo(3);
        assertThat(actual.get(3)).isNotNull();
        assertThat(actual.get(3).getId()).isEqualTo(4);

        verify(employeeJpaRepository, times(1)).findAll();
        verify(employeeJdbcRepository, times(1)).findAll();
    }
}
