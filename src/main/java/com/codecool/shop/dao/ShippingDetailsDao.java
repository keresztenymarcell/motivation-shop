package com.codecool.shop.dao;

import com.codecool.shop.model.ShippingDetails;

import java.util.List;

public interface ShippingDetailsDao {

    void add(ShippingDetails item);
    ShippingDetails find(int userId);
    void update(ShippingDetails shippingDetails);
    void remove(int id);
    List<ShippingDetails> getAll(int orderId);

}
