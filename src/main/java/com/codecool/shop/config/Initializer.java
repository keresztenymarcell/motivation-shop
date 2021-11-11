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
        ProductCategory elixir = new ProductCategory("Elixir", "Consumable", "An enchanting drink to enhance your powers.");
        productCategoryDataStore.add(elixir);
        ProductCategory poster = new ProductCategory("Poster", "Picture", "A motivating piece of paper.");
        productCategoryDataStore.add(poster);
        ProductCategory pet = new ProductCategory("Pet", "Pet", "An actual animal that you need to care for.");
        productCategoryDataStore.add(pet);
        ProductCategory plant = new ProductCategory("Plant", "Plant", "A literal plant. Not as motivating as a pet, but still dies if you don't take care of it.");
        productCategoryDataStore.add(plant);


        User testUser = new User("Teszt Tamás");
        userDataStore.add(testUser);
        testUser.setOrder(new Order(testUser));


        //setting up products and printing it
        productDataStore.add(new Product("Julian Treasure", new BigDecimal("8.500"), "USD", "How to speak so that people want to listen.", event, ted));
        productDataStore.add(new Product("Bill Gates", new BigDecimal("12.500"), "USD", "The next outbreak? We are not ready.", event, ted));
        productDataStore.add(new Product("Just Do It rights", new BigDecimal("479"), "USD", "Dooooooooo it!.", trade, nike));
        productDataStore.add(new Product("Never give up!", new BigDecimal("100"), "USD", "I never lose. I either win or I learn.", trade, cola));
        productDataStore.add(new Product("Puppy Poster", new BigDecimal("100"), "USD", "It's not real, but it's cute and motivates you.", poster, amazon));


        Supplier witchCraftBrewery = new Supplier("Witchcraft Brewery", "The most enchanting beverages from all the realms");
        supplierDataStore.add(witchCraftBrewery);
        Supplier absolut = new Supplier("Absolut", "Original Sweden alcoholic products");
        supplierDataStore.add(absolut);

        productDataStore.add(new Product("Potion of Happiness", new BigDecimal("30"), "USD", "The potion of choice for anyone feeling a bit under the weather.", elixir, absolut));
        productDataStore.add(new Product("Potion of Motivation", new BigDecimal("666.69"), "USD", "Do you feel tired after a long TW week? This beverage can fill you with Determination for your upcoming SI tasks like nothing else!", elixir, witchCraftBrewery));
        productDataStore.add(new Product("Potion of Inspiration", new BigDecimal("999.99"), "USD", "Better than the kiss of any muse.", elixir, witchCraftBrewery));
        productDataStore.add(new Product("Potion of Endurance", new BigDecimal("399.99"), "USD", "The best potion to keep your muscles strong and your heart brave whenever you need some extra help.", elixir, witchCraftBrewery));
        productDataStore.add(new Product("Potion of Vigilance", new BigDecimal("449.99"), "USD", "To keep your eyes peeled even in the greatest of exhaustion.", elixir, witchCraftBrewery));

        Supplier perfectPetShop = new Supplier("Perfect Pet Shop", "The perfect shop for perfect wannabe pet owners");
        supplierDataStore.add(perfectPetShop);
        Supplier averagePlantShop = new Supplier("Average Plant Shop", "Not too meh plants for those with no patience for better creatures");
        supplierDataStore.add(averagePlantShop);

        productDataStore.add(new Product("Cool Talking Parrot", new BigDecimal("100"), "USD", "It's a pretty cool parrot. You can teach it to swear at you if you don't study or sing the American anthem while you meditate.", pet, perfectPetShop));
        productDataStore.add(new Product("Lazy Turtle", new BigDecimal("30"), "USD", "Pretty lazy. If you don't do your job, you'll end up like it.", pet, perfectPetShop));
        productDataStore.add(new Product("Cactus", new BigDecimal("15"), "USD", "The perfect starter for responsible adults. If you manage to kill this one, there is no hope for you.", plant, averagePlantShop));
        productDataStore.add(new Product("Philodendron", new BigDecimal("30"), "USD", "Green means good job. Brown means bad job.", plant, averagePlantShop));







    }
}
