package com.codecool.shop.model;


import javax.sound.sampled.Line;
import java.util.HashSet;
import java.util.Set;

public class Order extends BaseModel{



    private final Set<LineItem> cart = new HashSet<>();
    private int userId;

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
    }

    public void removeItemFromCart(LineItem item){

        if(checkIfItemInCart(item)){
            LineItem current = cart.stream().filter(x -> x.getName().equals(item.getName())).findFirst().get();
            current.setQuantity(current.getQuantity() - 1);
            if(current.getQuantity() == 0){
                cart.remove(current);
            }
        }
    }

    private boolean checkIfItemInCart(LineItem item){
        return cart.stream().anyMatch(lineItem -> lineItem.getName().equals(item.getName()));
    }

    public Set<LineItem> getCart() {
        return cart;
    }




}
