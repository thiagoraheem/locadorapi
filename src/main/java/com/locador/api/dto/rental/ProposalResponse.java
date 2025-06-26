package com.locador.api.dto.rental;

import com.locador.api.model.rental.Proposal;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProposalResponse {
    @NotNull
    private Integer customerId;

    private List<ProposalItemRequest> proposalItemRequests;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private LocalDate endDate;
    private BigDecimal estimatedAmount;
    private String notes;

    public ProposalResponse(){}

    public ProposalResponse(Proposal proposal){
        this.proposalItemRequests = proposal.getProposalItems();
        this.customerId = proposal.getCustomer().getId();
        this.endDate = proposal.getStartDate();
        this.endDate = proposal.getEndDate();
        this.estimatedAmount = proposal.getEstimatedAmount();
        this.notes = proposal.getNotes();
    }

    public ProposalResponse(Integer customerId,
                           LocalDate startDate, LocalDate endDate,
                           BigDecimal estimatedAmount, String notes){
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedAmount = estimatedAmount;
        this.notes = notes;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }



    public LocalDate getStartDate() {
        return startDate;
    }

    public List<ProposalItemRequest> getProposalItemRequests() {
        return proposalItemRequests;
    }

    public void setProposalItemRequests(List<ProposalItemRequest> proposalItemRequests) {
        this.proposalItemRequests = proposalItemRequests;
    }

    public BigDecimal getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(BigDecimal estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
}
