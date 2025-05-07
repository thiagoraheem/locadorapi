package com.locador.api.model.financial;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_type")
public class InvoiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
