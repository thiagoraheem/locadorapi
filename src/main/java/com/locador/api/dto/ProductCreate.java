package com.locador.api.dto;

import java.math.BigDecimal;

public class ProductCreate {
    private int category_id;
    private String description;
    private String brand;
    private String model;
    private BigDecimal costPrice;
    private BigDecimal rentalPrice;
    private String status;
    private int type_id;
    
    public int getCategory_id() {
        return category_id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public BigDecimal getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }
    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getType_id() {
        return type_id;
    }
    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

}
