package com.codecool.shop.model;

public class User extends BaseModel{

    private String password;
    private String email;
    private ShoppingCart cart;

    private Order order;

    public User(String name, ShoppingCart cart) {
        super(name);
        this.cart = cart;
    }

    public boolean hasOrder(){
        return order != null;
    }

    public Order getOrder() {
        if (order == null){
            order = new Order(this);
        }
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public String getEmail() {
        return email;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}
