package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {

    ShoppingCart createShoppingCart(int userId);
    void addToShoppingCart(int userId, Product product);
    void deleteShoppingCart(int userId);
    void removeFromShoppingCart(int userId, Product product);



}
