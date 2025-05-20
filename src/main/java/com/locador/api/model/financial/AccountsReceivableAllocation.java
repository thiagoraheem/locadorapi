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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountsReceivable getReceivable() {
        return receivable;
    }

    public void setReceivable(AccountsReceivable receivable) {
        this.receivable = receivable;
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public ChartOfAccounts getAccount() {
        return account;
    }

    public void setAccount(ChartOfAccounts account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
