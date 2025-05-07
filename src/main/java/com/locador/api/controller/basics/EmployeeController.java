package com.locador.api.controller.basics;

import org.springframework.web.bind.annotation.RestController;

import com.locador.api.model.basics.Employee;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/employee")
public class EmployeeController
{

    @GetMapping
    public List<Employee> getAll()
    {
        return null;
    }

}
