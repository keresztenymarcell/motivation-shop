package com.codecool.shop.model;

public class User extends BaseModel{

    private String password;
    private String email;
    private Order order;

    public User(String name) {
        super(name);
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
}
