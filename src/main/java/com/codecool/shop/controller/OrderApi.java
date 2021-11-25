package com.codecool.shop.controller;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ShoppingCartService;
import com.codecool.shop.util.InputValidator;
import com.codecool.shop.util.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;


@WebServlet(name = "orderApi", urlPatterns = {"/api/order"}, loadOnStartup = 1)
public class OrderApi extends HttpServlet {

    ShoppingCartService shoppingCartService = ServiceProvider.getShoppingCartService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ShoppingCart shoppingCart = shoppingCartService.getCartByUser(1);
        System.out.println(shoppingCart);

        InputValidator.createJsonFromObject(resp, shoppingCart);

    }
}