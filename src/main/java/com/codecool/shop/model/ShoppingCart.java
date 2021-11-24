package com.codecool.shop.model;

import javax.sound.sampled.Line;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<LineItem> lineItems = new ArrayList<>();
    private BigDecimal totalPrice = new BigDecimal(0);


    public void addToShoppingCart(Product product){
        boolean alreadyInCart = false;

        for(LineItem item : lineItems){
            if(item.getProduct().equals(product)){
                alreadyInCart = true;
                item.setQuantity(item.getQuantity()+1);
            }
        }
        if(!alreadyInCart){
            lineItems.add(new LineItem(product));
        }
        totalPrice = totalPrice.add(product.getDefaultPrice());
    }

    public void removeFromShoppingCart(Product product){
        for(LineItem item : lineItems){
            if(item.getProduct().equals(product)){
                if(item.getQuantity() != 0){
                    item.setQuantity(item.getQuantity()-1);
                }
                else{
                    lineItems.remove(item);
                }
            }
            totalPrice = totalPrice.subtract(product.getDefaultPrice());
        }
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getTotalItems(){
        int totalItems = 0;
        for (LineItem lineItem : lineItems) {
            totalItems += lineItem.getQuantity();
        }
        return totalItems;
    }
}
