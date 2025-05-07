package com.locador.api.model.basics;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_real_estate")
public class ProductRealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private String registrationNumber;
    private String stateRegistration;
    private String propertyType;
    private BigDecimal usefulArea;
    private BigDecimal totalArea;
    private Boolean hasRegistry;
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    
    public String getStateRegistration() {
        return stateRegistration;
    }
    
    public void setStateRegistration(String stateRegistration) {
        this.stateRegistration = stateRegistration;
    }
    
    public String getPropertyType() {
        return propertyType;
    }
    
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    
    public BigDecimal getUsefulArea() {
        return usefulArea;
    }
    
    public void setUsefulArea(BigDecimal usefulArea) {
        this.usefulArea = usefulArea;
    }
    
    public BigDecimal getTotalArea() {
        return totalArea;
    }
    
    public void setTotalArea(BigDecimal totalArea) {
        this.totalArea = totalArea;
    }
    
    public Boolean getHasRegistry() {
        return hasRegistry;
    }
    
    public void setHasRegistry(Boolean hasRegistry) {
        this.hasRegistry = hasRegistry;
    }
}