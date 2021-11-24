package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.jdbc.*;

import java.sql.SQLException;

public class DatabaseManager extends DatabaseConnection{

    ShoppingCartDao lineItemDao;
    ProductCategoryDao productCategoryDao;
    ProductDao productDao;
    SupplierDao supplierDao;
    UserDao userDao;

    public void setup() throws SQLException{
        connect();
        lineItemDao = new ShoppingCartDaoJdbc(dataSource);
        productCategoryDao = new ProductCategoryDaoJdbc();
        productDao = new ProductDaoJdbc();
        supplierDao = new SupplierDaoJdbc();
        userDao = new UserDaoJdbc();

    }

}
