package com.codecool.shop.util;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.jdbc.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.jdbc.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.jdbc.UserDaoJdbc;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.dao.implementation.mem.UserDaoMem;
import com.codecool.shop.service.ProductService;

import java.sql.SQLException;

public class ServiceProvider {

    private final static String connectionType = Initializer.getConnectionType();


    public static ProductService getService() throws SQLException {
        ProductDao productDataStore = null;
        SupplierDao supplierDataStore = null;
        ProductCategoryDao productCategoryDataStore = null;
        UserDao userDataStore = null;

        if(connectionType.equals("memory")){
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();
            userDataStore = UserDaoMem.getInstance();
            return new ProductService(productDataStore,productCategoryDataStore,supplierDataStore, userDataStore);
        }
        else if(connectionType.equals("jdbc")){
            ProductDaoJdbc.getInstance().connect();
            productDataStore = ProductDaoJdbc.getInstance();

            SupplierDaoJdbc.getInstance().connect();
            supplierDataStore = SupplierDaoJdbc.getInstance();

            ProductCategoryDaoJdbc.getInstance().connect();
            productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();

            UserDaoJdbc.getInstance().connect();
            userDataStore = UserDaoJdbc.getInstance();

        }
        return new ProductService(productDataStore,productCategoryDataStore,supplierDataStore, userDataStore);
    }




}
