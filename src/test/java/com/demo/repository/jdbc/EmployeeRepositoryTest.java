package com.demo.repository.jdbc;

import com.demo.mapper.EmployeeMapper;
import com.demo.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class EmployeeRepositoryTest {
    @InjectMocks
    private EmployeeJdbcRepository target;

    @Mock
    @Qualifier("namedParameterJdbcTemplate2")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate2;

    @Mock
    private EmployeeMapper employeeMapper;

    private List<Employee> employees;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Employee employee1 = new Employee();
        employee1.setId(1);

        Employee employee2 = new Employee();
        employee2.setId(2);

        List<Employee> employees1 = Arrays.asList(employee1, employee2);

        employees = new ArrayList<>();
        employees.addAll(employees1);

        when(namedParameterJdbcTemplate2.query(anyString(), any(RowMapper.class))).thenReturn(employees1);
    }

    @Test
    public void findAll() {
        List<Employee> actual = target.findAll();

        assertThat(actual).isEqualTo(employees);
        verify(namedParameterJdbcTemplate2, times(1))
                .query("SELECT * FROM EMPLOYEES", employeeMapper);
    }
}
