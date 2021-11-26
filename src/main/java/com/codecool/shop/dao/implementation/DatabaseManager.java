package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.jdbc.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager{
    LineItemDao lineItemDao;
    ProductCategoryDao productCategoryDao;
    ProductDao productDao;
    SupplierDao supplierDao;
    UserDao userDao;
    OrderDao orderDao;
    ShippingBillingDao shippingBillingDao;

    public void setup() throws SQLException{
        DatabaseConnection dc = new DatabaseConnection();
        DataSource ds = dc.connect();


        lineItemDao = new LineItemDaoJdbc();
        productCategoryDao = new ProductCategoryDaoJdbc();
        productDao = new ProductDaoJdbc();
        supplierDao = new SupplierDaoJdbc();
        userDao = new UserDaoJdbc();
        orderDao = new OrderDaoJdbc();
        shippingBillingDao = new ShippingBillingDaoJdbc();
    }

}
