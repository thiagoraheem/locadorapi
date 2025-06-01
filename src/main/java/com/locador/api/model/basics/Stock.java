package com.locador.api.model.basics;

import com.locador.api.dto.basics.StockRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private Integer quantity;
    private Integer minimumQuantity;

    public Stock(){}

    public Stock(StockRequest stockRequest){
        this.quantity = stockRequest.getQuantity();
        this.minimumQuantity = stockRequest.getMinimumQuantity();
        ProductCategory productCategory = new ProductCategory(1, "nameCategory", true);
        ProductType productType = new ProductType(1, "nameType");
        this.product = new Product(stockRequest.getProduct_id(), "descricao", "marca", "modelo", BigDecimal.valueOf(1), BigDecimal.valueOf(2), "disponivel", productCategory, productType);
    }
    
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