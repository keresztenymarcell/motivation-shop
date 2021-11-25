package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LineItemDaoJdbc extends DatabaseConnection implements LineItemDao {
    private static LineItemDaoJdbc instance;
    //private OrderDaoJdbc orderDaoJdbc = OrderDaoJdbc.getInstance();
    private ProductDaoJdbc productDaoJdbc = ProductDaoJdbc.getInstance();

    private LineItemDaoJdbc(){}

    public static LineItemDaoJdbc getInstance() {
        if (instance == null) {
            instance = new LineItemDaoJdbc();
        }
        return instance;
    }
    @Override
    public void add(LineItem item) {

    }

    @Override
    public LineItem find(int id) {
        try(Connection conn = dataSource.getConnection()){
            String query = String.format("select * from line_items where id = %d", id);
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {

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
}
