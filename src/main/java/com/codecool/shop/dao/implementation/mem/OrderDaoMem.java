package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoMem implements OrderDao {

    private Map<Integer, Order> data = new HashMap<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        data.put(order.getUserId(), order);
    }

    @Override
    public Order find(int id) {
        return data.get(id);
    }

    @Override
    public void update(Order order) {
        //
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll(int orderId) {
        return null;
    }
}
