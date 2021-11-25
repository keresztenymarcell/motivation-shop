package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import javax.sql.DataSource;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
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
        ShoppingCart cart = new ShoppingCart();
        return cart;
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
        String query = "select id, user_id from shopping_carts where user_id = ?";
        connect();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if(!result.next()){
                return null;
            }
            int cartId = result.getInt(1);
            ShoppingCart cart = new ShoppingCart();
            ArrayList<LineItem> lineItems = LineItemDaoJdbc.getInstance().getAll(cartId);
            cart.setLineItems(lineItems);

            return cart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
