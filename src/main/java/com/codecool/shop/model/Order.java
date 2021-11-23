package com.codecool.shop.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import javax.mail.*;
import javax.mail.internet.*;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Order extends BaseModel{
    private final Set<LineItem> cart = new HashSet<>();
    private int userId;
    private int totalItems;
    private BigDecimal orderTotalValue;
    private boolean isSuccessPayment = false;
    private String paymentMethod;
    private String orderTime;
    private ShippingDetails shippingDetails;

    public Order(User user) {
        this.userId = user.getId();
        user.setOrder(this);
        shippingDetails = new ShippingDetails();
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

    public void emptyCart(){
        cart.clear();
        totalItems = 0;
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
        return cart.stream().map(LineItem::getItemTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public ShippingDetails getShippingDetails() {
        return shippingDetails;
    }
}
