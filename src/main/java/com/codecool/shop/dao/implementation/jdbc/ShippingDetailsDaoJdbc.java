package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ShippingDetailsDao;
import com.codecool.shop.model.ShippingDetails;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShippingDetailsDaoJdbc extends DatabaseConnection implements ShippingDetailsDao {

    private static ShippingDetailsDaoJdbc instance;

    public static ShippingDetailsDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ShippingDetailsDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ShippingDetails shippingDetails) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO shipping_descriptions (country, zip_code, address, name, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, shippingDetails.getCountry());
            statement.setString(2, shippingDetails.getZipcode());
            statement.setString(3, shippingDetails.getAddress());
            statement.setString(4, shippingDetails.getOrderName());
            statement.setString(5, shippingDetails.getEmail());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            shippingDetails.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShippingDetails find(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT country, zip_code, address, name, email FROM shipping_descriptions WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            String country = resultSet.getString(1);
            String zip_code = resultSet.getString(2);
            String address = resultSet.getString(3);
            String name = resultSet.getString(4);
            String email = resultSet.getString(5);

            ShippingDetails shippingDetails = new ShippingDetails(country, zip_code, address, name, email);
            shippingDetails.setId(id);
            return shippingDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ShippingDetails shippingDetails) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE shipping_descriptions SET country = ?, zip_code = ?, address = ?, name = ?, email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, shippingDetails.getCountry());
            statement.setString(2, shippingDetails.getZipcode());
            statement.setString(3, shippingDetails.getAddress());
            statement.setString(4, shippingDetails.getOrderName());
            statement.setString(5, shippingDetails.getEmail());
            statement.setInt(6, shippingDetails.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ShippingDetails> getAll(int orderId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, country, zip_code, address, name, email FROM shipping_descriptions";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            List<ShippingDetails> results = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String country = resultSet.getString(2);
                String zip_code = resultSet.getString(3);
                String address = resultSet.getString(4);
                String name = resultSet.getString(5);
                String email = resultSet.getString(6);

                ShippingDetails shippingDetails = new ShippingDetails(country, zip_code, address, name, email);
                shippingDetails.setId(id);
                results.add(shippingDetails);
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}