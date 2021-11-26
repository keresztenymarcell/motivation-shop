package com.codecool.shop.service;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.mem.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import org.apache.log4j.Logger;

public class ShoppingCartService {

    ShoppingCartDao shoppingCartDao;
    ProductDao productDao;//done
    private final Logger logger = Logger.getLogger(Initializer.class);


    public ShoppingCartService(ShoppingCartDao shoppingCartDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.productDao = productDao;
    }


    public void addProductToCart(int userId, int productId){
        Product product = productDao.find(productId);
        shoppingCartDao.addToShoppingCart(userId, product);
        logger.info(String.format("User (ID=%d) added a product (ID=%d) to the cart!", userId, productId));


    }

    public void removeProductFromCart(int userId, int productId){
        Product product = productDao.find(productId);
        shoppingCartDao.removeFromShoppingCart(1, product);
        logger.info(String.format("User (ID=%d) removed a product (ID=%d) from the cart!", userId, productId));
    }

    public void emptyCart(int userId){
        shoppingCartDao.remove(userId);
        logger.info("User emptied their cart!");
    }

    public ShoppingCart getCartByUser(int userId){

        return shoppingCartDao.get(userId);
    }

}
