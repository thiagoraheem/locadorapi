package com.locador.api.model;

import com.locador.api.enumeracoes.OrcamentoStatus;
import com.locador.api.model.basics.Customer;
import com.locador.api.model.basics.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name =  "orcamento")
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer cliente;

    @ManyToMany
    @JoinColumn(name = "product_id")
    private List<Product> product;

    private LocalDate prazoInicial;
    private LocalDate prazoFinal;
    private BigDecimal valorPrevisto;

    @Enumerated(EnumType.STRING)
    private OrcamentoStatus orcamentoStatus;

    private String observacoes;

    public Orcamento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCliente() {
        return cliente;
    }

    public void setCliente(Customer cliente) {
        this.cliente = cliente;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public LocalDate getPrazoInicial() {
        return prazoInicial;
    }

    public void setPrazoInicial(LocalDate prazoInicial) {
        this.prazoInicial = prazoInicial;
    }

    public LocalDate getPrazoFinal() {
        return prazoFinal;
    }

    public void setPrazoFinal(LocalDate prazoFinal) {
        this.prazoFinal = prazoFinal;
    }

    public BigDecimal getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(BigDecimal valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public OrcamentoStatus getOrcamentoStatus() {
        return orcamentoStatus;
    }

    public void setOrcamentoStatus(OrcamentoStatus orcamentoStatus) {
        this.orcamentoStatus = orcamentoStatus;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
