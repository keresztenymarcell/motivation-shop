package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.HashSet;
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
        user.setCart(this);
        shippingDetails = new ShippingDetails();
    }

    public Order(User user, ShippingDetails shippingDetails, boolean isSuccessPayment, String paymentMethod, String orderTime) {
        this.userId = user.getId();
        user.setCart(this);
        this.shippingDetails = shippingDetails;

        this.isSuccessPayment = isSuccessPayment;
        this.paymentMethod = paymentMethod;
        this.orderTime = orderTime;
        this.totalItems = cart.size();
        this.orderTotalValue = getOrderTotalValue();
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

    public int getUserId() {
        return userId;
    }
}
