package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface ShippingBillingDao {
    void add(Order order);
    Order find(int id);
    void remove(int id);

    List<Order> getAll();
}
