package com.locador.api.dto.basics;

import com.locador.api.model.basics.Supplier;

import java.time.LocalDate;

public class SupplierResponse {

    private Integer id;
    private String name;
    private String document;
    private Integer personType_id;
    private String personType_name;
    private Integer address_id;
    private String address_street;
    private String address_number;
    private String address_complement;
    private String address_neighborhood;
    private String address_city;
    private String address_state;
    private String address_zipCode;
    private LocalDate registrationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPersonType_name() {
        return personType_name;
    }

    public void setPersonType_name(String personType_name) {
        this.personType_name = personType_name;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getAddress_number() {
        return address_number;
    }

    public void setAddress_number(String address_number) {
        this.address_number = address_number;
    }

    public String getAddress_complement() {
        return address_complement;
    }

    public void setAddress_complement(String address_complement) {
        this.address_complement = address_complement;
    }

    public String getAddress_neighborhood() {
        return address_neighborhood;
    }

    public void setAddress_neighborhood(String address_neighborhood) {
        this.address_neighborhood = address_neighborhood;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_state() {
        return address_state;
    }

    public void setAddress_state(String address_state) {
        this.address_state = address_state;
    }

    public String getAddress_zipCode() {
        return address_zipCode;
    }

    public void setAddress_zipCode(String address_zipCode) {
        this.address_zipCode = address_zipCode;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public SupplierResponse(){}

    public SupplierResponse(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.document = supplier.getDocument();

        if (supplier.getPersonType() != null) {
            this.personType_id = supplier.getPersonType().getId();
            this.personType_name = supplier.getPersonType().getName();
        }

        if (supplier.getAddress() != null) {
            this.address_id = supplier.getAddress().getId();
            this.address_street = supplier.getAddress().getStreet();
            this.address_number = supplier.getAddress().getNumber();
            this.address_complement = supplier.getAddress().getComplement();
            this.address_neighborhood = supplier.getAddress().getNeighborhood();
            this.address_city = supplier.getAddress().getCity();
            this.address_state = supplier.getAddress().getState();
            this.address_zipCode = supplier.getAddress().getZipCode();
        }

        this.registrationDate = supplier.getRegistrationDate();
    }

}