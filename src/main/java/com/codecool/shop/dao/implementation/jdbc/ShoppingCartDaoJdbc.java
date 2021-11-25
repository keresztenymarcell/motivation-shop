package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ShoppingCartDaoJdbc extends DatabaseConnection implements ShoppingCartDao {
    private static ShoppingCartDaoJdbc instance;

    public static ShoppingCartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoJdbc();
        }
        return instance;
    }

    @Override
    public ShoppingCart createShoppingCart(int userId) {
        return null;
    }

    @Override
    public void addToShoppingCart(int userId, Product product) {

    }


    @Override
    public void deleteShoppingCart(int userId) {

    }

    @Override
    public void removeFromShoppingCart(int userId, Product product) {

    }

    @Override
    public void remove(int userId) {

    }

    @Override
    public ShoppingCart get(int userId) {
        return null;
    }
}
