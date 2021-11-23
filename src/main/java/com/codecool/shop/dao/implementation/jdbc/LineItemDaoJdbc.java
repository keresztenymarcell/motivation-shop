package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.util.List;

public class LineItemDaoJdbc implements LineItemDao {

    DataSource dataSource;

    public LineItemDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }
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
