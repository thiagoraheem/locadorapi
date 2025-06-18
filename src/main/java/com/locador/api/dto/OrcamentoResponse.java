package com.locador.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrcamentoResponse {

    private Integer id;
    private String clienteNome;
    private String periodo;
    private BigDecimal valorTotal;
    private String status;
    private List<String> nomesProdutos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getNomesProdutos() {
        return nomesProdutos;
    }

    public void setNomesProdutos(List<String> nomesProdutos) {
        this.nomesProdutos = nomesProdutos;
    }
}
