package com.locador.api.dto.basics;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {

    @Positive(message = "O ID da categoria deve ser um número positivo")
    private int category_id;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @NotBlank(message = "A marca é obrigatória")
    private String brand;

    @NotBlank(message = "O modelo é obrigatório")
    private String model;

    @NotNull(message = "O preço de custo é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço de custo deve ser maior que zero")
    private BigDecimal costPrice;

    @NotNull(message = "O preço de aluguel é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço de aluguel deve ser maior que zero")
    private BigDecimal rentalPrice;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @Positive(message = "O ID do tipo deve ser um número positivo")
    private int type_id;

    @Positive(message = "O ID da proposta deve ser um número positivo")
    private Integer proposalId;

    @Positive(message = "O ID do produto deve ser um número positivo")
    private Integer productId;

    public ProductRequest() {
    }

    public ProductRequest(int category_id, String description, String brand, String model,
                          BigDecimal costPrice, BigDecimal rentalPrice, String status,
                          int type_id, Integer proposalId, Integer productId) {
        this.category_id = category_id;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.costPrice = costPrice;
        this.rentalPrice = rentalPrice;
        this.status = status;
        this.type_id = type_id;
        this.proposalId = proposalId;
        this.productId = productId;
    }

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

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}