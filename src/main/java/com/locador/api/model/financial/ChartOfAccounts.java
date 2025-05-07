package com.locador.api.model.financial;

import jakarta.persistence.*;

@Entity
@Table(name = "chart_of_accounts")
public class ChartOfAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}