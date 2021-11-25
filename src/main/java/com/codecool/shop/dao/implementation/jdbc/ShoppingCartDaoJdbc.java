package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import javax.sound.sampled.Line;
import javax.sql.DataSource;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoJdbc extends DatabaseConnection implements ShoppingCartDao {
    private static ShoppingCartDaoJdbc instance;
    private static LineItemDaoJdbc lineItemDaoJdbc = LineItemDaoJdbc.getInstance();

    public static ShoppingCartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoJdbc();
        }
        return instance;
    }



    @Override
    public ShoppingCart createShoppingCart(int userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO shopping_carts (user_id) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addToShoppingCart(int userId, Product product) {

        if(get(userId) != null){
            get(userId).addToShoppingCart(product);
            ShoppingCart cart = get(userId);
            ArrayList<LineItem> items = lineItemDaoJdbc.getAll(cart.getId());
            LineItem foundItem = items.stream().filter(x -> x.getCartId() == cart.getId()).findFirst().orElse(null);

            if (foundItem != null) {
                foundItem.setQuantity(foundItem.getQuantity() + 1);
                lineItemDaoJdbc.update(foundItem);
            } else {
                LineItem newLineItem = new LineItem(product);
                newLineItem.setCartId(cart.getId());
                newLineItem.setQuantity(1);
                lineItemDaoJdbc.add(newLineItem);
            }

        }
        else{
            ShoppingCart dummyCart = createShoppingCart(userId);
            ShoppingCart cart = get(userId);
            LineItem lineItem = new LineItem(product);
            lineItem.setCartId(cart.getId());
            lineItem.setQuantity(1);
            lineItemDaoJdbc.add(lineItem);




        }

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
        String query = "select id, user_id from shopping_carts where user_id = ? ORDER BY id desc limit 1";
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
            cart.setId(cartId);

            return cart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
