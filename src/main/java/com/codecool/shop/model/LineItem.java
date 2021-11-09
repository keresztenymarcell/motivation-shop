package com.codecool.shop.model;

import java.math.BigDecimal;

public class LineItem extends BaseModel{

    private int quantity;
    private final BigDecimal productPrice;


    public LineItem(Product product) {
        super(product.getName(), product.getDescription());
        this.quantity = 1;
        this.productPrice = product.getDefaultPrice();

    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public BigDecimal calculateTotal(){
        return productPrice.multiply(new BigDecimal(quantity));
    }

}
