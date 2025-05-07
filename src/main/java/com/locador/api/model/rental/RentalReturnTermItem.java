package com.locador.api.model.rental;

import com.locador.api.model.basics.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rental_return_term_item")
public class RentalReturnTermItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "return_term_id")
    private RentalReturnTerm returnTerm;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private Integer quantity;
    
    @Column(name = "condition_notes")
    private String conditionNotes;
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public RentalReturnTerm getReturnTerm() {
        return returnTerm;
    }
    
    public void setReturnTerm(RentalReturnTerm returnTerm) {
        this.returnTerm = returnTerm;
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
    
    public String getConditionNotes() {
        return conditionNotes;
    }
    
    public void setConditionNotes(String conditionNotes) {
        this.conditionNotes = conditionNotes;
    }
}