package com.codecool.shop.util;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.*;
import com.codecool.shop.service.Service;

import java.sql.SQLException;

public class ServiceProvider {

    private final static String connectionType = Initializer.getConnectionType();


    public static Service getService() throws SQLException {
        ProductDao productDataStore = null;
        SupplierDao supplierDataStore = null;
        ProductCategoryDao productCategoryDataStore = null;
        UserDao userDataStore = null;
        OrderDao orderDataStore = null;
        ShippingDetailsDao shippingDetailsDataStore = null;

        if(connectionType.equals("memory")){
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();
            userDataStore = UserDaoMem.getInstance();
            orderDataStore = OrderDaoMem.getInstance();
            shippingDetailsDataStore = ShippingDetailsDaoMem.getInstance();
            return new Service(productDataStore,productCategoryDataStore,supplierDataStore, userDataStore, orderDataStore, shippingDetailsDataStore);
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

            OrderDaoJdbc.getInstance().connect();
            orderDataStore = OrderDaoJdbc.getInstance();

            ShippingDetailsDaoJdbc.getInstance().connect();
            shippingDetailsDataStore = ShippingDetailsDaoJdbc.getInstance();

        }
        return new Service(productDataStore,productCategoryDataStore,supplierDataStore, userDataStore, orderDataStore, shippingDetailsDataStore);
    }




}
