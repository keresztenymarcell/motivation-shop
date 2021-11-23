package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class OrderDaoJdbc extends DatabaseConnection implements OrderDao {
    private static OrderDaoJdbc instance;
    private LineItemDaoJdbc lineItemDaoJdbc = LineItemDaoJdbc.getInstance();

    public static OrderDaoJdbc getInstance() {
        if (instance == null) {
            instance = new OrderDaoJdbc();
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
