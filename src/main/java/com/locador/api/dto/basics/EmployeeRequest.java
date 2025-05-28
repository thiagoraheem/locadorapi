package com.locador.api.dto.basics;

import com.locador.api.model.basics.Employee;

public class EmployeeRequest {

    private String name;
    private String cpf;
    private String email;
    private String phone;
    private Integer roleId;
    private String username;
    private String password;
    private Boolean isActive;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public EmployeeRequest(){}

    public EmployeeRequest(Employee employee) {
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.roleId = employee.getRoleId();
        this.username = employee.getUsername();
        this.password = employee.getPassword();
        this.isActive = employee.getIsActive();
    }
}
