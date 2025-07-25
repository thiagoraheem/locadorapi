package com.locador.api.model.basics;

import java.time.LocalDate;

import com.locador.api.dto.basics.SupplierRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    private String document;
    
    @ManyToOne
    @JoinColumn(name = "person_type_id")
    private PersonType personType;
    
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    
    private LocalDate registrationDate;

    public Supplier(){}

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
    
    public PersonType getPersonType() {
        return personType;
    }
    
    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    public Supplier(SupplierRequest supplierRequest) {
        this.name = supplierRequest.getName();
        this.document = supplierRequest.getDocument();
        this.personType = new PersonType(supplierRequest.getPersonType_id());
        this.address = new Address(supplierRequest.getAddress_id());
        this.registrationDate = supplierRequest.getRegistrationDate();
    }
}