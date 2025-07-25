package com.locador.api.model.rental;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rental_return_term")
public class RentalReturnTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
    
    @Column(name = "return_date")
    private LocalDate returnDate;
    
    @Column(name = "returned_by")
    private String returnedBy;
    
    @Column(name = "received_by")
    private String receivedBy;
    
    private String notes;
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Rental getRental() {
        return rental;
    }
    
    public void setRental(Rental rental) {
        this.rental = rental;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    public String getReturnedBy() {
        return returnedBy;
    }
    
    public void setReturnedBy(String returnedBy) {
        this.returnedBy = returnedBy;
    }
    
    public String getReceivedBy() {
        return receivedBy;
    }
    
    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
}