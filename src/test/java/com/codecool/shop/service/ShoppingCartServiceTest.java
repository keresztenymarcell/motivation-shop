package com.codecool.shop.service;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartServiceTest {

    ShoppingCartDao shoppingCartDao;
    ProductDao productDao;
    ShoppingCartService service;
    ShoppingCart mockCart;
    List<LineItem> cartItems;

    @BeforeEach
    void setup(){
        shoppingCartDao = mock(ShoppingCartDao.class);
        productDao = mock(ProductDao.class);
        mockCart = mock(ShoppingCart.class);
        service = new ShoppingCartService(shoppingCartDao, productDao);
        cartItems = new LinkedList<>(Arrays.asList(
                mock(LineItem.class),
                mock(LineItem.class),
                mock(LineItem.class)));
    }

    @Test
    void addProductToCart_successfulAddItem(){
        Product product = mock(Product.class);
        shoppingCartDao.addToShoppingCart(1, product);
        shoppingCartDao.addToShoppingCart(1, product);
        int expected = 2;

        assertEquals(expected, shoppingCartDao.get(1).getTotalItems());
    }

    @Test
    void emptyCart_whenCalledOnCart_emptiesCart(){

    }




}