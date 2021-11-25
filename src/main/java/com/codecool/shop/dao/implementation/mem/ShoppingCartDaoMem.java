package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private Map<Integer, ShoppingCart> shoppingCarts = new HashMap<>();
    private static ShoppingCartDaoMem instance = null;

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public ShoppingCart createShoppingCart(int userId) {
        ShoppingCart cart = new ShoppingCart();
        shoppingCarts.put(userId, cart);
        return cart;
    }

    @Override
    public void addToShoppingCart(int userId, Product product){
        if(shoppingCarts.get(userId)!=null){
            shoppingCarts.get(userId).addToShoppingCart(product);
        }else{
            ShoppingCart cart = createShoppingCart(userId);
            cart.addToShoppingCart(product);
        }
    }

    @Override
    public void removeFromShoppingCart(int userId, Product product) {
        shoppingCarts.get(userId).removeFromShoppingCart(product);

    }

    @Override
    public void remove(int userId) {
        shoppingCarts.remove(userId);
    }

    @Override
    public ShoppingCart get(int userId) {
        return shoppingCarts.get(userId);
    }
}
