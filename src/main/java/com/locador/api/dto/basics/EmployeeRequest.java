package com.locador.api.dto.basics;

import com.locador.api.model.basics.Employee;

public class EmployeeRequest {

    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EmployeeRequest(){}

    public EmployeeRequest(Employee employee) {
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.username = employee.getUsername();
    }
}
