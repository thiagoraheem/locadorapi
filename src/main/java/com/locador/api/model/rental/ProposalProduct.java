package com.locador.api.model.rental;

import com.locador.api.model.basics.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "proposal_product")
public class ProposalProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProposalProduct() {
    }

    public ProposalProduct(Integer proposalId, Integer productId) {
        this.proposal = new Proposal();
        this.proposal.setId(proposalId);
        this.product = new Product();
        this.product.setId(productId);
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

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
