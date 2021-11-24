package com.codecool.shop.util;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.*;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ShoppingCartService;

import java.sql.SQLException;

public class ServiceProvider {

    private final static String connectionType = Initializer.getConnectionType();

    static ProductDao productDataStore = null;
    static SupplierDao supplierDataStore = null;
    static ProductCategoryDao productCategoryDataStore = null;
    static UserDao userDataStore = null;
    static OrderDao orderDataStore = null;
    static ShippingDetailsDao shippingDetailsDataStore = null;
    static ShoppingCartDao shoppingCartDataStore = null;


    public static void init(){
        try {
            if (connectionType.equals("memory")) {
                productDataStore = ProductDaoMem.getInstance();
                productCategoryDataStore = ProductCategoryDaoMem.getInstance();
                supplierDataStore = SupplierDaoMem.getInstance();
                userDataStore = UserDaoMem.getInstance();
                orderDataStore = OrderDaoMem.getInstance();
                shippingDetailsDataStore = ShippingDetailsDaoMem.getInstance();

            } else if (connectionType.equals("jdbc")) {
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
        }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

    public static ProductService getProductService(){
        return new ProductService(productDataStore, productCategoryDataStore, supplierDataStore, userDataStore);
    }

    public static ShoppingCartService getShoppingCartService(){
        return new ShoppingCartService(shoppingCartDataStore, productDataStore);
    }

}

