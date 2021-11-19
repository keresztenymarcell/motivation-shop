package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import java.util.List;

public class Service {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    private UserDao userDao;

    public Service(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao, UserDao userDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
        this.userDao = userDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public Supplier getSupplier(int supplierId){
        return supplierDao.find(supplierId);
    }

    public Product getProduct(int productId){
        return productDao.find(productId);
    }

    public User getUser(int id){
        return userDao.find(id);
    }

    public List<Product> getProductsForSupplier(int supplierId){
        var supplier = supplierDao.find(supplierId);
        return productDao.getBy(supplier);
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }

}
