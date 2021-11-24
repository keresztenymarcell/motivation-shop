package com.codecool.shop.controller;

import com.codecool.shop.model.*;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ShoppingCartService;
import com.codecool.shop.util.InputValidator;
import com.codecool.shop.util.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "cartController", urlPatterns = {"/api/cart"}, loadOnStartup = 1)
public class AddToCartController extends HttpServlet {

    ShoppingCartService service = ServiceProvider.getShoppingCartService();
    ProductService productService = ServiceProvider.getProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = InputValidator.checkIntInput(req.getParameter("id"));
//        int quantity = InputValidator.checkIntInput(req.getParameter("quantity"));

        User currentUser = service.getUser(1);

        ShoppingCart cart = new ShoppingCart();
        Product product  = service.getProduct(id);
        LineItem lineItem = new LineItem(product);
        String currentTime;

        if(!currentUser.hasCart()){
            currentOrder = new Order(currentUser);
        }
        else{
            currentOrder = currentUser.getCart();
        }
        currentOrder.addItemToCart(lineItem);
        currentTime = InputValidator.formatLocalDateTimeNowToString();
        currentOrder.setOrderTime(currentTime);

        PaymentCredit.createJsonFromObject(resp, currentOrder);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService service = getService();
        User currentUser = service.getUser(1);
        currentUser.getCart().emptyCart();

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{}");
        out.flush();
    }

//    private ProductService getService(){
//        ProductService service = null;
//        try {
//            service = ServiceProvider.getShoppingCartService();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return service;
//    }
}
