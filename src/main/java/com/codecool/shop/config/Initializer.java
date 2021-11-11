package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        UserDao userDataStore = UserDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier ted = new Supplier("Ted", "TEDx is a program of local, self-organized events that bring people together to share a TED-like experience");
        supplierDataStore.add(ted);
        Supplier nike = new Supplier("Nike", "Sport brand");
        supplierDataStore.add(nike);
        Supplier cola = new Supplier("Coca-cola", "Drink factory");
        supplierDataStore.add(cola);

        //setting up a new product category
        ProductCategory event = new ProductCategory("Event", "Talks", "TEDx is a grassroots initiative, created in the spirit of TED’s overall mission to research and discover “ideas worth spreading.” TEDx brings the spirit of TED to local communities around the globe through TEDx events. These events are organized by passionate individuals who seek to uncover new ideas and to share the latest research in their local areas that spark conversations in their communities.");
        productCategoryDataStore.add(event);
        ProductCategory trade = new ProductCategory("Advert", "Video", "A motivating video.");
        productCategoryDataStore.add(trade);
        ProductCategory elixir = new ProductCategory("Elixir", "Consumable", "A drink.");
        productCategoryDataStore.add(elixir);
        ProductCategory poster = new ProductCategory("Poster", "Picture", "A motivating piece of paper.");
        productCategoryDataStore.add(poster);


        User testUser = new User("Teszt Tamás");
        userDataStore.add(testUser);
        testUser.setOrder(new Order(testUser));


        //setting up products and printing it
        productDataStore.add(new Product("Julian Treasure", new BigDecimal("8.500"), "USD", "How to speak so that people want to listen.", event, ted));
        productDataStore.add(new Product("Bill Gates", new BigDecimal("12.500"), "USD", "The next outbreak? We are not ready.", event, ted));
        productDataStore.add(new Product("Just Do It rights", new BigDecimal("479"), "USD", "Dooooooooo it!.", trade, nike));
        productDataStore.add(new Product("Never give up!", new BigDecimal("100"), "USD", "I never lose. I either win or I learn.", trade, cola));
        productDataStore.add(new Product("Puppy Poster", new BigDecimal("100"), "USD", "A cute puppy to motivate you.", poster, amazon));

    }
}
