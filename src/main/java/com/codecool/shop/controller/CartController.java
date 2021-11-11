package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.service.Service;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@WebServlet(name = "cartController", urlPatterns = {"/api/add-to-cart"}, loadOnStartup = 1)
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        UserDao userDataStore = UserDaoMem.getInstance();
        Service service = new Service(productDataStore,productCategoryDataStore,supplierDataStore, userDataStore);


        int id;

        try {
            id = Integer.parseInt(req.getParameter("id"));
            if (id < 1) {
                id = 1;
            }
        } catch (NumberFormatException e) {
            id = 1;
        }

        Order currentOrder;
        Product product  = service.getProduct(id);
        LineItem lineItem = new LineItem(product);

        User currentUser = service.getUser(1);

        if(!currentUser.hasOrder()){
            currentOrder = new Order(currentUser);
        }
        else{
            currentOrder = currentUser.getOrder();
        }
        currentOrder.addItemToCart(lineItem);
        currentOrder.setOrderTime(LocalDateTime.now().toString());

        PaymentCredit.createJsonFromObject(resp, currentOrder);
    }

}
