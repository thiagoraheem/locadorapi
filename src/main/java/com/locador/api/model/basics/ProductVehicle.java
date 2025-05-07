package com.locador.api.model.basics;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_vehicle")
public class ProductVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private String plate;
    private String renavam;
    private String chassis;
    private Integer manufacturingYear;
    private Integer modelYear;
    private String fuelType;
    private String color;
    private String licenseState;
    private String licenseCity;
    
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
    
    public String getPlate() {
        return plate;
    }
    
    public void setPlate(String plate) {
        this.plate = plate;
    }
    
    public String getRenavam() {
        return renavam;
    }
    
    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }
    
    public String getChassis() {
        return chassis;
    }
    
    public void setChassis(String chassis) {
        this.chassis = chassis;
    }
    
    public Integer getManufacturingYear() {
        return manufacturingYear;
    }
    
    public void setManufacturingYear(Integer manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }
    
    public Integer getModelYear() {
        return modelYear;
    }
    
    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getLicenseState() {
        return licenseState;
    }
    
    public void setLicenseState(String licenseState) {
        this.licenseState = licenseState;
    }
    
    public String getLicenseCity() {
        return licenseCity;
    }
    
    public void setLicenseCity(String licenseCity) {
        this.licenseCity = licenseCity;
    }
}