package com.locador.api.model.rental;

import com.locador.api.dto.rental.ProposalRequest;
import com.locador.api.enumeracoes.rental.ProposalStatus;
import com.locador.api.model.basics.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProposalItem> proposalItems;

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal estimatedAmount;

    @Enumerated(EnumType.STRING)
    private ProposalStatus proposalStatus;

    private String notes;

    public Proposal() {
    }

    public Proposal(ProposalRequest proposalRequest){
        this.customer.setId(proposalRequest.getCustomerId());
        this.startDate = proposalRequest.getStartDate();
        this.endDate = proposalRequest.getEndDate();
        this.estimatedAmount = proposalRequest.getEstimatedAmount();
        this.notes = proposalRequest.getNotes();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProposalItem> getProposalItems() {
        return proposalItems;
    }

    public void setProposalItems(List<ProposalItem> proposalItems) {
        this.proposalItems = proposalItems;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(BigDecimal estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }

    public void setProposalStatus(ProposalStatus proposalStatus) {
        this.proposalStatus = proposalStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
