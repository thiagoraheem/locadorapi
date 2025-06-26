package com.locador.api.model.rental;

import com.locador.api.dto.rental.ProposalItemRequest;
import com.locador.api.dto.rental.ProposalRequest;
import com.locador.api.model.basics.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "proposal_product")
public class ProposalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    private int proposalId;

    private int productId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String description;
    private Integer quantity;
    private double amount;
    private double totalAmount;

    public ProposalItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
