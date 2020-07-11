package com.demo.repository.jpa;

import com.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeJpaRepository extends CrudRepository<Employee, Integer> {
}
