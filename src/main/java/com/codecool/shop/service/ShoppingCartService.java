package com.codecool.shop.service;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.mem.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

public class ShoppingCartService {

    ShoppingCartDao shoppingCartDao;
    ProductDao productDao;//done


    public ShoppingCartService(ShoppingCartDao shoppingCartDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.productDao = productDao;
    }


    public void addProductToCart(int userId, int productId){
        Product product = productDao.find(productId);
        shoppingCartDao.addToShoppingCart(userId, product);

    }

    public void removeProductFromCart(int userId, int productId){
        Product product = productDao.find(productId);
        shoppingCartDao.removeFromShoppingCart(1, product);

    }

    public void emptyCart(int userId){
        shoppingCartDao.remove(userId);
    }

    public ShoppingCart getCartByUser(int userId){
        return shoppingCartDao.get(userId);

    }


}
