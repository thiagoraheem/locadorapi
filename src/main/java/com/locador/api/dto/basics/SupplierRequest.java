package com.locador.api.dto.basics;

import java.time.LocalDate;

public class SupplierRequest {

    private String name;
    private String document;
    private Integer personType_id;
    private Integer address_id;
    private LocalDate registrationDate;

    public SupplierRequest() {}

    public SupplierRequest(String name, String document, Integer personType_id, Integer address_id, LocalDate registrationDate) {
        this.name = name;
        this.document = document;
        this.personType_id = personType_id;
        this.address_id = address_id;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getPersonType_id() {
        return personType_id;
    }

    public void setPersonType_id(Integer personType_id) {
        this.personType_id = personType_id;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}