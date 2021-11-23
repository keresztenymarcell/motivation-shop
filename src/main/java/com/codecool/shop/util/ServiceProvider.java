package com.codecool.shop.util;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.dao.implementation.mem.UserDaoMem;
import com.codecool.shop.service.Service;

public class ServiceProvider {

    private final static String connectionType = Initializer.getConnectionType();


    public static Service getService() {

        if(connectionType.equals("memory")){
            ProductDao productDataStore = ProductDaoMem.getInstance();
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
            UserDao userDataStore = UserDaoMem.getInstance();
            return new Service(productDataStore,productCategoryDataStore,supplierDataStore, userDataStore);
        }
        else if(connectionType.equals("jdbc")){
            return null;




        }


    }




}
