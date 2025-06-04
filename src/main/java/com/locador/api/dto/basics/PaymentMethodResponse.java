package com.locador.api.dto.basics;

import com.locador.api.model.basics.PaymentMethod;

public class PaymentMethodResponse {

    private Integer id;
    private String name;

    public PaymentMethodResponse() {}

    public PaymentMethodResponse(PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.name = paymentMethod.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
