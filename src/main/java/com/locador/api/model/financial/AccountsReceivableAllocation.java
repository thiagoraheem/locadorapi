package com.locador.api.model.financial;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts_receivable_allocation")
public class AccountsReceivableAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "receivable_id")
    private AccountsReceivable receivable;

    @ManyToOne
    @JoinColumn(name = "cost_center_id")
    private CostCenter costCenter;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private ChartOfAccounts account;

    private BigDecimal amount;
}
