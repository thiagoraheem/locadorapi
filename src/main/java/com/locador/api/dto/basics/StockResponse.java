package com.locador.api.dto.basics;

import com.locador.api.model.basics.Stock;

import java.math.BigDecimal;

public class StockResponse {

    private Integer id;
    private Integer quantity;
    private Integer minimumQuantity;
    private Integer productCategory_id;
    private String productCategory_name;
    private Boolean productCategory_status;
    private String product_description;
    private String product_brand;
    private String product_model;
    private BigDecimal product_costPrice;
    private BigDecimal product_rentalPrice;
    private String product_status;
    private Integer productType_id;
    private String productType_name;

    public StockResponse(){}

        public StockResponse(Stock stock) {
            this.id = stock.getId();
            this.quantity = stock.getQuantity();
            this.minimumQuantity = stock.getMinimumQuantity();
            if (stock.getProduct().getCategory() != null) {
                this.productCategory_id = stock.getProduct().getCategory().getId();
                this.productCategory_name = stock.getProduct().getCategory().getName();
                this.productCategory_status = stock.getProduct().getCategory().getStatus();
            }
            if (stock.getProduct() != null) {
                this.product_description = stock.getProduct().getDescription();
                this.product_brand = stock.getProduct().getBrand();
                this.product_model = stock.getProduct().getModel();
                this.product_costPrice = stock.getProduct().getCostPrice();
                this.product_rentalPrice = stock.getProduct().getRentalPrice();
                this.product_status = stock.getProduct().getStatus();
            }
            if (stock.getProduct().getType() != null) {
                this.productType_id = stock.getProduct().getType().getId();
                this.productType_name = stock.getProduct().getType().getName();
            }
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductCategory_id() {
        return productCategory_id;
    }

    public void setProductCategory_id(Integer productCategory_id) {
        this.productCategory_id = productCategory_id;
    }


    public String getProductCategory_name() {
        return productCategory_name;
    }

    public void setProductCategory_name(String productCategory_name) {
        this.productCategory_name = productCategory_name;
    }

    public Boolean getProductCategory_status() {
        return productCategory_status;
    }

    public void setProductCategory_status(Boolean productCategory_status) {
        this.productCategory_status = productCategory_status;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public String getProduct_model() {
        return product_model;
    }

    public void setProduct_model(String product_model) {
        this.product_model = product_model;
    }

    public BigDecimal getProduct_costPrice() {
        return product_costPrice;
    }

    public void setProduct_costPrice(BigDecimal product_costPrice) {
        this.product_costPrice = product_costPrice;
    }

    public BigDecimal getProduct_rentalPrice() {
        return product_rentalPrice;
    }

    public void setProduct_rentalPrice(BigDecimal product_rentalPrice) {
        this.product_rentalPrice = product_rentalPrice;
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }

    public Integer getProductType_id() {
        return productType_id;
    }

    public void setProductType_id(Integer productType_id) {
        this.productType_id = productType_id;
    }

    public String getProductType_name() {
        return productType_name;
    }

    public void setProductType_name(String productType_name) {
        this.productType_name = productType_name;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }
}
