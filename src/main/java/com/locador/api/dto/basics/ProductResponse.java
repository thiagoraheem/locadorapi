package com.locador.api.dto.basics;

import com.locador.api.model.basics.Product;

import java.math.BigDecimal;

public class ProductResponse {

    private Integer id;
    private int category_id;
    private String category_name;
    private boolean category_status;
    private String description;
    private String brand;
    private String model;
    private BigDecimal costPrice;
    private BigDecimal rentalPrice;
    private String status;
    private int type_id;
    private String type_name;
    private int proposalId;
    private int productId;

    public ProductResponse() {
    }

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.category_id = product.getCategory().getId();
        this.category_name = product.getCategory().getName();
        this.category_status = product.getCategory().getStatus();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.model = product.getModel();
        this.costPrice = product.getCostPrice();
        this.rentalPrice = product.getRentalPrice();
        this.status = product.getStatus();
        this.type_id = product.getType().getId();
        this.type_name = product.getType().getName();
        this.proposalId = product.getProposalProducts().get(0).getProposal().getId();
        this.productId = product.getProposalProducts().get(0).getProduct().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public boolean getCategory_status() {
        return category_status;
    }

    public void setCategory_status(boolean category_status) {
        this.category_status = category_status;
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}