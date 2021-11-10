package com.codecool.shop.model;


import com.google.gson.Gson;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Order extends BaseModel{
    private final Set<LineItem> cart = new HashSet<>();
    private int userId;
    private int totalItems;
    private BigDecimal orderTotalValue;
    private boolean orderSuccessful = true;
    private String paymentMode;

    public Order(User user) {
        this.userId = user.getId();
        user.setOrder(this);
    }

    public void addItemToCart(LineItem item){
        if(checkIfItemInCart(item)){
            LineItem current = cart.stream().filter(x -> x.getName().equals(item.getName())).findFirst().get();
            current.setQuantity(current.getQuantity() + 1);
        }
        else{
            cart.add(item);
        }
        this.orderTotalValue = getOrderTotalValue();
        this.totalItems++;
    }

    public void removeItemFromCart(LineItem item){
        if(checkIfItemInCart(item)){
            LineItem current = cart.stream().filter(x -> x.getName().equals(item.getName())).findFirst().get();
            current.setQuantity(current.getQuantity() - 1);
            if(current.getQuantity() == 0){
                cart.remove(current);
            }
        }
        if(totalItems!=0){
            this.totalItems--;
        }
        this.orderTotalValue = getOrderTotalValue();

    }

    private boolean checkIfItemInCart(LineItem item){
        return cart.stream().anyMatch(lineItem -> lineItem.getName().equals(item.getName()));
    }

    public Set<LineItem> getCart() {
        return cart;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public BigDecimal getOrderTotalValue(){
        return cart.stream().map(X -> X.getItemTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public void saveToJson(){
        Gson gson = new Gson();

    }

    public void sendEmail(){

    }

}
