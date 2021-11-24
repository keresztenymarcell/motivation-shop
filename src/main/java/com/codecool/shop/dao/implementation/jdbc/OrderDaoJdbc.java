package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ShippingDetailsDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJdbc extends DatabaseConnection implements OrderDao {

    private static OrderDaoJdbc instance;
    UserDao userDao = UserDaoJdbc.getInstance();
    ShippingDetailsDao shippingDetailsDao = ShippingDetailsDaoJdbc.getInstance();

    public static OrderDaoJdbc getInstance() {
        if (instance == null) {
            instance = new OrderDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO orders (shipping_description_id, user_id, payment_method) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getShippingDetails().getId());
            statement.setInt(2, order.getUserId());
            statement.setString(3, order.getPaymentMethod());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            order.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order find(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT shipping_description_id, user_id, payment_method FROM orders WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            int shippingDescriptionId = resultSet.getInt(1);
            int userId = resultSet.getInt(2);
            String paymentMethod = resultSet.getString(3);

            User user = userDao.find(userId);
            ShippingDetails shippingDetails = shippingDetailsDao.find(shippingDescriptionId);


            Order order = new Order(user, shippingDetails, true, paymentMethod, "timeNeedsImplementing"); // need to connect to User and Shipping Description jdbcs, to fill stuff
            order.setId(id);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE orders SET shipping_description_id = ?, user_id = ?, payment_method = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getShippingDetails().getId());
            statement.setInt(2, order.getUserId());
            statement.setString(3, order.getPaymentMethod());
            statement.setInt(4, order.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll(int userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, shipping_description_id, user_id, payment_method FROM orders";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            List<Order> results = new ArrayList<>();

            User user = userDao.find(userId);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int shippingDescriptionId = resultSet.getInt(2);
                int userIdFromDB = resultSet.getInt(3);
                String paymentMethod = resultSet.getString(4);

                ShippingDetails shippingDetails = shippingDetailsDao.find(shippingDescriptionId);

                Order order = new Order(user, shippingDetails, true, paymentMethod, "timeNeedsImplementing");
                order.setId(id);

                results.add(order);
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
