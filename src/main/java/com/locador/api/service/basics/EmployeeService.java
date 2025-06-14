package com.locador.api.service.basics;

import com.locador.api.dto.basics.EmployeeRequest;
import com.locador.api.dto.basics.EmployeeResponse;
import com.locador.api.model.basics.Employee;
import com.locador.api.repository.basics.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeResponse> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for(int i = 0; i < employees.size(); i++){
            employeeResponses.add(new EmployeeResponse(employees.get(i)));
        }
        return employeeResponses;
    }

    public Optional<EmployeeResponse> findById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(EmployeeResponse::new);
    }

    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest);
        employeeRepository.save(employee);
        return new EmployeeResponse(employee);
    }

    public EmployeeResponse update(Integer id, EmployeeRequest employeeRequest) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        Employee employee = new Employee(employeeRequest);
        employee.setId(id);
        employeeRepository.save(employee);
        return new EmployeeResponse(employee);
    }


    public EmployeeResponse changeActive(Integer id, boolean isActive) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        employee.setIsActive(isActive);
        employeeRepository.save(employee);

        return new EmployeeResponse(employee);
    }

    public EmployeeResponse updatePassword(Integer id, String password) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        employee.setPassword(password);
        employeeRepository.save(employee);

        return new EmployeeResponse(employee);
    }

    public EmployeeResponse updateRoleId(Integer id, Integer roleId) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        employee.setRoleId(roleId);
        employeeRepository.save(employee);
        return new EmployeeResponse(employee);
    }

    public void delete(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        employeeRepository.deleteById(id);
    }
    
}
