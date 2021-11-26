package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ShippingBillingDao;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.util.List;

public class ShippingBillingDaoJdbc implements ShippingBillingDao {

    private DataSource dataSource;
    public ShippingBillingDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
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
