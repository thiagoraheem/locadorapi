package com.locador.api.dto.basics;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import com.locador.api.model.basics.Product;

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

    @Positive(message = "O ID do tipo deve ser um número positivo e não pode ser nulo")
    private int type_id;

    public int getCategory_id() { return category_id; }
    public void setCategory_id(int category_id) { this.category_id = category_id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public BigDecimal getCostPrice() { return costPrice; }
    public void setCostPrice(BigDecimal costPrice) { this.costPrice = costPrice; }

    public BigDecimal getRentalPrice() { return rentalPrice; }
    public void setRentalPrice(BigDecimal rentalPrice) { this.rentalPrice = rentalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getType_id() { return type_id; }
    public void setType_id(int type_id) { this.type_id = type_id; }

    public ProductRequest() { }

    public ProductRequest(Product product) {
        this.category_id = product.getCategory().getId();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.model = product.getModel();
        this.costPrice = product.getCostPrice();
        this.rentalPrice = product.getRentalPrice();
        this.status = product.getStatus();
        this.type_id = product.getType().getId();
    }
}
