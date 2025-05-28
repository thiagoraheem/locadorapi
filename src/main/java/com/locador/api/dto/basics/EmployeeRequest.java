package com.locador.api.dto.basics;

import com.locador.api.model.basics.Employee;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String name;
    @NotBlank(message = "O cpf é obrigatório")
    private String cpf;
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;
    @NotBlank(message = "O número de telefone é obrigatório")
    private String phone;
    @NotBlank(message = "O nome de usuário é obrigatório")
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
