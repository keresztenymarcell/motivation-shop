package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class LineItem extends BaseModel{
    private int quantity;
    private Product product;

    public LineItem(Product product) {
        super(product.getName(), product.getDescription());
        this.product = product;
        this.quantity = 1;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getItemTotal() {
        return product.getDefaultPrice().multiply(new BigDecimal(quantity));
    }

    public Currency getCurrency() {
        return product.getDefaultCurrency();
    }

    public int getProductId() {
        return product.getId();
    }
}
