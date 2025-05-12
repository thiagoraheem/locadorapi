package com.locador.api.service.basics;

import com.locador.api.model.basics.Employee;
import com.locador.api.repository.basics.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Integer id, Employee employee) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void delete(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        employeeRepository.deleteById(id);
    }
    
}
