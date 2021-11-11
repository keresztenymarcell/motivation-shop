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

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory elixir = new ProductCategory("Elixir", "Consumable", "A drink.");
        productCategoryDataStore.add(elixir);
        ProductCategory poster = new ProductCategory("Poster", "Picture", "A motivating piece of paper.");
        productCategoryDataStore.add(poster);


        User testUser = new User("Teszt Tamás");
        userDataStore.add(testUser);
        testUser.setOrder(new Order(testUser));


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("TestLa", new BigDecimal("9999"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", elixir, amazon));
        productDataStore.add(new Product("Puppy Poster", new BigDecimal("100"), "USD", "A cute puppy to motivate you.", poster, amazon));


        Supplier witchCraftBrewery = new Supplier("Witchcraft Brewery", "The most enchanting beverages from all the realms");
        supplierDataStore.add(witchCraftBrewery);
        Supplier absolut = new Supplier("Absolut", "Original Sweden alcoholic products");
        supplierDataStore.add(absolut);

        productDataStore.add(new Product("Potion of Happiness", new BigDecimal("666.69"), "USD", "The potion of choice for anyone feeling a bit under the weather.", elixir, absolut));
        productDataStore.add(new Product("Potion of Motivation", new BigDecimal("666.69"), "USD", "Do you feel tired after a long TW week? This beverage can fill you with Determination for your upcoming SI tasks like nothing else!", elixir, witchCraftBrewery));
        productDataStore.add(new Product("Potion of Inspiration", new BigDecimal("666.69"), "USD", "Better than the kiss of any muse.", elixir, witchCraftBrewery));
        productDataStore.add(new Product("Potion of Endurance", new BigDecimal("399.99"), "USD", "The best potion to keep your muscles strong and your heart brave whenever you need some extra help.", elixir, witchCraftBrewery));
        productDataStore.add(new Product("Potion of Vigilance", new BigDecimal("449.99"), "USD", "To keep your eyes peeled even in the greatest of exhaustion.", elixir, witchCraftBrewery));

        Supplier perfectPetShop = new Supplier("Perfect Pet Shop", "The perfect shop for perfect wannabe pet owners");
        supplierDataStore.add(perfectPetShop);
        Supplier averagePlantShop = new Supplier("Average Plant Shop", "Not too meh plants for those with no patience for better creatures");
        supplierDataStore.add(averagePlantShop);









    }
}
