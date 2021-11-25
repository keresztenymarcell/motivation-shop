package com.codecool.shop.service;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.*;
import com.codecool.shop.util.InputValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    private UserDao userDao;
    private final Logger logger = Logger.getLogger(Initializer.class);

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao, UserDao userDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
        this.userDao = userDao;
    }

    //index page filter help methods
    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        logger.info("Get product by category!");
        return productDao.getBy(category);
    }

    public List<Product> getProductsForSupplier(int supplierId){
        var supplier = supplierDao.find(supplierId);
        logger.info("Get product by supplier!");
        return productDao.getBy(supplier);
    }

    //index page display All help methods
    public List<Product> getAllProducts() {
        logger.info("Get all product!");
        return productDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        logger.info("Get all supplier!");
        return supplierDao.getAll();
    }

    public List<ProductCategory> getAllCategories() {
        logger.info("Get all categories!");
        return productCategoryDao.getAll();
    }

}
