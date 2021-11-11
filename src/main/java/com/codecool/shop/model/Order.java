package com.codecool.shop.model;


import com.google.gson.Gson;

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
import java.util.HashSet;
import java.util.Properties;
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
    }

    public void sendEmail() throws MessagingException {
        Properties prop = new Properties();
        String d_email = "vinczeg1281@gmail.com";
        String d_host = "smtp.gmail.com";
        String d_port = "465";
        prop.put("mail.smtp.user", d_email);
        prop.put("mail.smtp.host", d_host);
        prop.put("mail.smtp.port", d_port);
//        prop.put("mail.smtp.starttls.enable","false");
        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", d_port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");


        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vinczeg1281@gmail.com", System.getenv("EMAIL_PASSWORD"));
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("vinczeg1281@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("cantataprofana1930@gmail.com"));
        message.setSubject("Order Confirmation");

        Gson gson = new Gson();
        String json = gson.toJson(this);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(json, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    public void saveCheckout(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String filename = "src/main/webapp/order/checkouts" + id + ".json";
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
