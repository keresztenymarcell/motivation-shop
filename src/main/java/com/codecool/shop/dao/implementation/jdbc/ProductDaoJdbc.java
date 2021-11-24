package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoJdbc extends DatabaseConnection implements ProductDao {
    private static ProductDaoJdbc instance;
    private ProductCategoryDaoJdbc productCategoryDaoJDBC = ProductCategoryDaoJdbc.getInstance();
    private SupplierDaoJdbc supplierDaoJDBC = SupplierDaoJdbc.getInstance();

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        try {
            Connection conn = dataSource.getConnection();
            String query = String.format("insert into products values (%d %s %s %f %s %d %d)",
                    product.getId(), product.getName(), product.getDescription(), product.getDefaultPrice(),
                    product.getDefaultCurrency().getCurrencyCode(), product.getProductCategory().getId(),
                    product.getSupplier().getId());
            PreparedStatement statement = conn.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        return null;
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
