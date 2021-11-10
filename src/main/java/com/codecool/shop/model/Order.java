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
    private String orderName;
    private String email;
    private String country;
    private String zipcode;
    private String address;
    private boolean isSuccessPayment = false;
    private String paymentMethod;


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

    public boolean isSuccessPayment() {
        return isSuccessPayment;
    }

    public void setSuccessPayment(boolean successPayment) {
        isSuccessPayment = successPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getAddress() {
        return address;
    }
}
