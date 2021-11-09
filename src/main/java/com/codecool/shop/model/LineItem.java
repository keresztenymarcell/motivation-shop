package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class LineItem extends BaseModel{
    private int quantity;
    private final BigDecimal productPrice;
    private BigDecimal itemTotal;
    private int productId;
    private Currency  currency;


    public LineItem(Product product) {
        super(product.getName(), product.getDescription());
        this.quantity = 1;
        this.productPrice = product.getDefaultPrice();
        this.productId = product.getId();
        this.itemTotal = product.getDefaultPrice();
        this.currency = product.getDefaultCurrency();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.itemTotal = calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    private BigDecimal calculateTotal(){
        return productPrice.multiply(new BigDecimal(quantity));
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public Currency getCurrency() {
        return currency;
    }
}
