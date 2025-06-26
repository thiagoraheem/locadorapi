package com.locador.api.dto.rental;

import com.locador.api.model.rental.ProposalItem;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class  ProposalRequest {

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

    public ProposalRequest(){}

    public ProposalRequest(Integer customerId, List<ProposalItemRequest> proposalItemRequests,
                           LocalDate startDate, LocalDate endDate,
                           BigDecimal estimatedAmount, String notes){
        this.customerId = customerId;
        this.proposalItemRequests = proposalItemRequests;
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

    public List<ProposalItemRequest> getProposalItemRequests() {
        return proposalItemRequests;
    }

    public void setProposalItemRequests(List<ProposalItemRequest> proposalItemRequests) {
        this.proposalItemRequests = proposalItemRequests;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
