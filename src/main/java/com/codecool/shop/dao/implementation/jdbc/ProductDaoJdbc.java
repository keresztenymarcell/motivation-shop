package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT name, description, default_price, default_currency"

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
