package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.List;

public class LineItemDaoMem implements LineItemDao {

    @Override
    public void add(LineItem item) {
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<LineItem> getAll(int orderId) {
        return null;
    }
}