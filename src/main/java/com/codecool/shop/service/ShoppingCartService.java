package com.codecool.shop.service;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.mem.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;

public class ShoppingCartService {

    ShoppingCartDao shoppingCartDao;
    ProductDao productDao;


    public ShoppingCartService(ShoppingCartDao shoppingCartDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.productDao = productDao;
    }


    public void addProductToCart(int userId, int productId){
        Product product = productDao.find(productId);
        shoppingCartDao.addToShoppingCart(userId, product);

    }


}
