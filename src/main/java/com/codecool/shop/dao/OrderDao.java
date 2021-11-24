package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    void add(Order order);
    Order find(int userId);
    void update(Order order);
    void remove(int id);
    List<Order> getAll(int orderId);

}
