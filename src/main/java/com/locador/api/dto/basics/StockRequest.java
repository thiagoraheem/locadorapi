package com.locador.api.dto.basics;

public class StockRequest {

    private Integer product_id;
    private Integer quantity;
    private Integer minimumQuantity;

    public StockRequest(){}

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }
}
