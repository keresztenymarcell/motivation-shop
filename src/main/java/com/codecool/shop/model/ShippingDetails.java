package com.codecool.shop.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShippingDetails extends BaseModel{
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
    private int userId;
    private String order_time;

    public ShippingDetails() {
    }

    public ShippingDetails(int userId, String orderName, String email, String country, String zipcode,
                           String address, String order_time) {
        this.orderName = orderName;
        this.email = email;
        this.country = country;
        this.zipcode = zipcode;
        this.address = address;
        this.userId = userId;
        this.order_time = order_time;
    }

    public void saveToJson() throws IOException {
        String filename = "src/main/webapp/order" + id + ".json";
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

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public int getUserId() {
        return userId;
    }
}
