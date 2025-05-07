package com.locador.api.model.financial;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.locador.api.model.basics.Customer;
import com.locador.api.model.rental.Rental;;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private InvoiceType type;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal totalAmount;
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> items;
}
