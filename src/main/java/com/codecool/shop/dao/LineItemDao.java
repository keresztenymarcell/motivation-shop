package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.List;

public interface LineItemDao {

    void add(LineItem item);
    LineItem find(int id);
    void remove(int id);
    void removeByCartId(int cartId);
    List<LineItem> getAll(int orderId);
    void update(LineItem item);
    LineItem getLineItemByProductId(int productId);
}
