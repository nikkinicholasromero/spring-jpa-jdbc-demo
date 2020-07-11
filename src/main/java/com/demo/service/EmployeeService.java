package com.demo.service;

import com.demo.model.Employee;
import com.demo.repository.jdbc.EmployeeJdbcRepository;
import com.demo.repository.jpa.EmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    private EmployeeJdbcRepository employeeJdbcRepository;

    public List<Employee> findAll() {
        List<Employee> employees1 = StreamSupport
                .stream(employeeJpaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        List<Employee> employees2 = employeeJdbcRepository.findAll();

        return Stream.concat(employees1.stream(), employees2.stream()).collect(Collectors.toList());
    }
}
