package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ShippingBillingDao;
import com.codecool.shop.model.Order;

import java.util.List;

public class ShippingBillingDaoJdbc extends DatabaseConnection implements ShippingBillingDao {
    private static ShippingBillingDaoJdbc instance;

    public static ShippingBillingDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ShippingBillingDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
