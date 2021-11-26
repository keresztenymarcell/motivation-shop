package com.codecool.shop.util;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.DatabaseManager;
import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.*;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ShoppingCartService;
import com.codecool.shop.service.UserService;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

public class ServiceProvider {

    private final static String connectionType = Initializer.getConnectionType();
    private DataSource dataSource = DatabaseConnection.connect();



    static ProductService productService;

    static ProductDao productDataStore = null;
    static SupplierDao supplierDataStore = null;
    static ProductCategoryDao productCategoryDataStore = null;
    static UserDao userDataStore = null;
    static OrderDao orderDataStore = null;
    static ShippingDetailsDao shippingDetailsDataStore = null;
    static ShoppingCartDao shoppingCartDataStore = null;


    public static ProductService getProductService(){
        if(productService == null){
            if (connectionType.equals("memory")) {
                productDataStore = ProductDaoMem.getInstance();
                productCategoryDataStore = ProductCategoryDaoMem.getInstance();
                supplierDataStore = SupplierDaoMem.getInstance();
                userDataStore = UserDaoMem.getInstance();
                productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore, userDataStore);
                return productService;

            } else if (connectionType.equals("jdbc")) {
                productDataStore = ProductDaoJdbc.getInstance();
                supplierDataStore = SupplierDaoJdbc.getInstance();
                userDataStore = UserDaoJdbc.getInstance();
                productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore, userDataStore);
                return productService;
            }
        }
        else{
            return productService;
        }
        return null;
        }

    public static ShoppingCartService getShoppingCartService(){
        if(connectionType.equals("memory")){
            productDataStore = ProductDaoMem.getInstance();
            shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
            return new ShoppingCartService(shoppingCartDataStore, productDataStore);
        }
        else if(connectionType.equals("jdbc")){
            ProductDaoJdbc.getInstance().connect();
            productDataStore = ProductDaoJdbc.getInstance();
            ShoppingCartDaoJdbc.getInstance().connect();
            shoppingCartDataStore = ShoppingCartDaoJdbc.getInstance();
            return new ShoppingCartService(shoppingCartDataStore, productDataStore);
        }
        return null;
    }

    public static UserService getUserService(){
        if(connectionType.equals("memory")){
            userDataStore = UserDaoMem.getInstance();
            return new UserService(userDataStore);

        }
        else if(connectionType.equals("jdbc")){
            UserDaoJdbc.getInstance().connect();
            userDataStore = UserDaoJdbc.getInstance();
            return new UserService(userDataStore);
        }
        return null;
    }

    public static OrderService getOrderService(){
        if(connectionType.equals("memory")){
            orderDataStore = OrderDaoMem.getInstance();
            shippingDetailsDataStore = ShippingDetailsDaoMem.getInstance();
            userDataStore = UserDaoMem.getInstance();
            return new OrderService(orderDataStore, shippingDetailsDataStore, userDataStore);

        }
        else if(connectionType.equals("jdbc")){
            OrderDaoJdbc.getInstance().connect();
            orderDataStore = OrderDaoJdbc.getInstance();
            ShippingDetailsDaoJdbc.getInstance().connect();
            shippingDetailsDataStore = ShippingDetailsDaoJdbc.getInstance();
            UserDaoJdbc.getInstance().connect();
            userDataStore = UserDaoJdbc.getInstance();
            return new OrderService(orderDataStore, shippingDetailsDataStore, userDataStore);
        }
        return null;

    }

}

