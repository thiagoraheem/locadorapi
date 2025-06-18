package com.locador.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class OrcamentoRequest {

    @NotNull
    private Integer clienteId;

    @NotNull
    @FutureOrPresent
    private LocalDate prazoInicial;

    @NotNull
    @FutureOrPresent
    private LocalDate prazoFinal;

    @NotNull
    @Size(min = 1)
    private List<Integer> produtoIds;

    // Getters e Setters

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
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

    public List<Integer> getProdutoIds() {
        return produtoIds;
    }

    public void setProdutoIds(List<Integer> produtoIds) {
        this.produtoIds = produtoIds;
    }
}
