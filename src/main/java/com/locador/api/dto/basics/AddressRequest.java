package com.locador.api.dto.basics;

import jakarta.validation.constraints.NotBlank;

public class AddressRequest {

    @NotBlank(message = "A rua é obrigatória")
    private String street;
    @NotBlank(message = "O número é obrigatório")
    private String number;
    private String complement; // Não obrigatório
    @NotBlank(message = "O bairro é obrigatório")
    private String neighborhood;
    @NotBlank(message = "A cidade é obrigatória")
    private String city;
    @NotBlank(message = "O estado é obrigatório")
    private String state;
    @NotBlank(message = "O código postal é obrigatório")
    private String zipCode;


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
