package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.jdbc.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager extends DatabaseConnection{

    LineItemDao lineItemDao;
    ProductCategoryDao productCategoryDao;
    ProductDao productDao;
    SupplierDao supplierDao;
    UserDao userDao;

    public void setup() throws SQLException{
        connect();
        lineItemDao = new LineItemDaoJdbc(dataSource);
        productCategoryDao = new ProductCategoryDaoJdbc();
        productDao = new ProductDaoJdbc();
        supplierDao = new SupplierDaoJdbc();
        userDao = new UserDaoJdbc();

    }

}
