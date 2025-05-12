package com.locador.api.repository.basics;

import com.locador.api.model.basics.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
