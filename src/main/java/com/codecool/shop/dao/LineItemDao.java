package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.List;

public interface LineItemDao {

    void add(LineItem item);
    Product find(int id);
    void remove(int id);
    List<LineItem> getAll(int orderId);

}