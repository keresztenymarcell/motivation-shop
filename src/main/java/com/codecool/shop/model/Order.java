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
    @Expose
    private String orderName;
    @Expose
    private String email;
    @Expose
    private String country;
    @Expose
    private String zipcode;
    @Expose
    private String address;
    private boolean isSuccessPayment = false;
    private String paymentMethod;
    private String orderTime;


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

    public void saveToJson() throws IOException {
//        String filename = System.getProperty("user.home") + "/Documents/Textfiles/" + "order" + id + ".json";
        String filename = "src/main/webapp/order" + id + ".json";
//        Path path = Paths.get(filename);
//        Files.createFile(path);
        FileWriter writer = new FileWriter(filename);

        Gson gson = new Gson();
        String json = gson.toJson(this);

        writer.write(json, 0, json.length());
        writer.close();

        saveCheckout();
    }


    void saveCheckout() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date millis = new Date(System.currentTimeMillis());
        String date = formatter.format(millis);
        String filename = "src/main/webapp/checkouts/" + id + "-" + date + ".json";

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(this);
        System.out.println(json);

        json = json.concat("\n\nOrder confirmed");
        System.out.println(json);
        FileWriter writer = new FileWriter(filename);
        writer.write(json, 0, json.length());
        writer.close();

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

    public String getAddress() {
        return address;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
