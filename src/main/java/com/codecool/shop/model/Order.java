package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Order extends BaseModel{
    private final ShoppingCart cart;
    private int userId;
    private int totalItems;
    private BigDecimal orderTotalValue;

    private boolean isSuccessPayment = false;
    private String paymentMethod;
    private String orderTime;
    private ShippingDetails shippingDetails;

    public Order(User user, ShippingDetails shippingDetails) {
        this.userId = user.getId();
        this.shippingDetails = shippingDetails;
        this.cart = user.getCart();
    }

    public Order(User user, ShippingDetails shippingDetails, boolean isSuccessPayment, String paymentMethod, String orderTime) {
        this.userId = user.getId();
        this.cart = user.getCart();
        this.shippingDetails = shippingDetails;

        this.isSuccessPayment = isSuccessPayment;
        this.paymentMethod = paymentMethod;
        this.orderTime = orderTime;
        this.totalItems = cart.getTotalItems();
        this.orderTotalValue = cart.getTotalPrice();
    }


    public int getTotalItems() {
        return totalItems;
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
