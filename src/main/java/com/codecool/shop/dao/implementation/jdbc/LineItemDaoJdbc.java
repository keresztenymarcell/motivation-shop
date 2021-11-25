package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LineItemDaoJdbc extends DatabaseConnection implements LineItemDao {
    private static LineItemDaoJdbc instance;
    //private OrderDaoJdbc orderDaoJdbc = OrderDaoJdbc.getInstance();
    //private ProductDaoJdbc productDaoJdbc = ProductDaoJdbc.getInstance();
    private final Logger logger = Logger.getLogger(Initializer.class);
    private ProductDaoJdbc productDaoJdbc = ProductDaoJdbc.getInstance();

    public LineItemDaoJdbc(){}

    public static LineItemDaoJdbc getInstance() {
        if (instance == null) {
            instance = new LineItemDaoJdbc();
        }
        return instance;
    }
    @Override
    public void add(LineItem item) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO line_items (product_id, cart_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, item.getProductId());
            statement.setInt(2, item.getCartId());
            statement.setInt(3, item.getQuantity());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            item.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            logger.error("OrderDao add SQL exception!");
            throw new RuntimeException(e);
        }

    }

    @Override
    public LineItem find(int id) {
        try(Connection conn = dataSource.getConnection()){
            String query = String.format("select * from line_items where id = %d", id);
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("LineItem find SQL exception!");
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE from line_items WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeByCartId(int cartId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE from line_items WHERE cart_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cartId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<LineItem> getAll(int cartId) {
        String query = "select id, product_id, quantity from line_items where cart_id = ?";
        connect();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, cartId);
            ResultSet result = statement.executeQuery();
            ArrayList<LineItem> items = new ArrayList<>();

            while(result.next()){
                int id = result.getInt(1);
                int productId = result.getInt(2);
                int quantity = result.getInt(3);
                Product product = productDaoJdbc.find(productId);
                LineItem lineItem = new LineItem(product);
                lineItem.setQuantity(quantity);
                lineItem.setId(id);
                items.add(lineItem);
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(LineItem item) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE line_items SET quantity = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getQuantity());
            statement.setInt(2, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LineItem getLineItemByProductId(int productId) {
        try(Connection conn = dataSource.getConnection()){
            String query = String.format("select * from line_items where product_id = %d", productId);
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return null;
            }
            int id = result.getInt(1);

            int cartId = result.getInt(3);
            int quantity = result.getInt(4);

            Product product = productDaoJdbc.find(productId);
            LineItem lineItem = new LineItem(product);
            lineItem.setCartId(cartId);
            lineItem.setId(id);
            lineItem.setQuantity(quantity);

            return lineItem;

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("LineItem find SQL exception!");
        }
        return null;
    }


}
