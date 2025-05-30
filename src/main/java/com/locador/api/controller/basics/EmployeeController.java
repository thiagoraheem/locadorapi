package com.locador.api.controller.basics;

import com.locador.api.dto.basics.EmployeeRequest;
import com.locador.api.dto.basics.EmployeeResponse;
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
    public ResponseEntity<List<EmployeeResponse>> getAll()
    {
        try{
            List<EmployeeResponse> employeesResponse = employeeService.findAll();
            if(employeesResponse.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(employeesResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Integer id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.save(employeeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        try {
            return ResponseEntity.ok(employeeService.update(id, employeeRequest));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/isactive")
    public ResponseEntity<EmployeeResponse> changeActive(@PathVariable Integer id, boolean isActive) {
        try {
            return ResponseEntity.ok(employeeService.changeActive(id, isActive));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<EmployeeResponse> updatePassword(@PathVariable Integer id, @RequestBody String password) {
        try {
            return ResponseEntity.ok(employeeService.updatePassword(id, password));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/roleId")
    public ResponseEntity<EmployeeResponse> updateRoleId(@PathVariable Integer id, Integer roleId) {
        try {
            return ResponseEntity.ok(employeeService.updateRoleId(id, roleId));
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
