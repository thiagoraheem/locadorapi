package com.locador.api.controller.basics;

import com.locador.api.model.basics.Employee;
import com.locador.api.service.basics.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeeController
{

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll()
    {
        try{
            List<Employee> employees = employeeService.findAll();
            if(employees.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(employees);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            return ResponseEntity.ok(employeeService.update(id, employee));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
