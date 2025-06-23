package com.locador.api.model.basics;

import com.locador.api.model.rental.Proposal;
import java.math.BigDecimal;
import java.util.List;

import com.locador.api.dto.basics.ProductRequest;
import com.locador.api.model.rental.ProposalProduct;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProposalProduct> proposalProducts;

    private String description;
    private String brand;
    private String model;
    private BigDecimal costPrice;
    private BigDecimal rentalPrice;
    private String status;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ProductType type;

    public Product() {
    }

    public Product(Integer id, String description, String brand, String model, BigDecimal costPrice,
                   BigDecimal rentalPrice, String status, ProductCategory productCategory,
                   ProductType productType) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.costPrice = costPrice;
        this.rentalPrice = rentalPrice;
        this.status = status;
        this.category = productCategory;
        this.type = productType;
    }

    public Product(ProductRequest productRequest) {
        this.id = productRequest.getProductId();
        this.category = new ProductCategory(productRequest.getCategory_id(), "", true);
        this.description = productRequest.getDescription();
        this.brand = productRequest.getBrand();
        this.model = productRequest.getModel();
        this.costPrice = productRequest.getCostPrice();
        this.rentalPrice = productRequest.getRentalPrice();
        this.status = productRequest.getStatus();
        this.type = new ProductType(productRequest.getType_id(), "");

        Proposal proposal = new Proposal();
        proposal.setId(productRequest.getProposalId());

        ProposalProduct proposalProduct = new ProposalProduct();
        proposalProduct.setProposal(proposal);

        this.proposalProducts = List.of(proposalProduct);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<ProposalProduct> getProposalProducts() {
        return proposalProducts;
    }

    public void setProposalProducts(List<ProposalProduct> proposalProducts) {
        this.proposalProducts = proposalProducts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
