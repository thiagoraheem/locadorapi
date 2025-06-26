package com.locador.api.dto.rental;

import com.locador.api.model.rental.ProposalItem;

public class ProposalItemRequest {

    private Integer proposalId;
    private Integer productId;
    private String description;
    private Integer quantity;
    private double amount;

    public ProposalItemRequest() {
    }

    public ProposalItemRequest(Integer proposalId, Integer productId,
                               String description, Integer quantity,
                               double amount){
        this.proposalId = proposalId;
        this.productId = productId;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
    }

    public ProposalItemRequest(ProposalItem proposalItem){
        this.proposalId = proposalItem.getProposalId();
        this.productId = proposalItem.getProductId();
        this.description = proposalItem.getDescription();
        this.quantity = proposalItem.getQuantity();
        this.amount = proposalItem.getAmount();
    }

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
