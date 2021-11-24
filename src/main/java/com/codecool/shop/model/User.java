package com.codecool.shop.model;

public class User extends BaseModel{

    private String password;
    private String email;
    private ShoppingCart cart;


    public User(String name) {
        super(name);
    }

    public boolean hasCart(){
        return cart != null;
    }


    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }


    public ShoppingCart getCart() {
        return cart;
    }

    public String getEmail() {
        return email;
    }


}
