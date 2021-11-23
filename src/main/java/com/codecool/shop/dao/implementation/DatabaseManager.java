package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.jdbc.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {

    LineItemDao lineItemDao;
    ProductCategoryDao productCategoryDao;
    ProductDao productDao;
    SupplierDao supplierDao;
    UserDao userDao;

    public void setup() throws SQLException{
        DataSource dataSource = connect();
        lineItemDao = new LineItemDaoJdbc(dataSource);
        productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        userDao = new UserDaoJdbc(dataSource);

    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        System.out.println("Trying to connect....");
        dataSource.getConnection().close();
        System.out.println("Connection OK!");

        return dataSource;
    }
}
