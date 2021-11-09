package com.codecool.shop.model;

public class User extends BaseModel{

    private String password;
    private String email;
    private Order order;

    public User(String name) {
        super(name);
    }



}
