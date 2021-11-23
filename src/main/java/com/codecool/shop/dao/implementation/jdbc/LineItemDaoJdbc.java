package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.util.List;

public class LineItemDaoJdbc extends DatabaseConnection implements LineItemDao {
    private static LineItemDaoJdbc instance;
    private OrderDaoJdbc orderDaoJdbc = OrderDaoJdbc.getInstance();
    private ProductDaoJdbc productDaoJdbc = ProductDaoJdbc.getInstance();


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
