package com.codecool.shop.controller;

import com.codecool.shop.model.Order;
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

@WebServlet(name = "paymentCredit", urlPatterns = {"/payment/credit"}, loadOnStartup = 1)
public class PaymentCredit extends HttpServlet {

    ShoppingCartService shoppingCartService = ServiceProvider.getShoppingCartService();
    OrderService orderService = ServiceProvider.getOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Order order = orderService.getOrder(1);
        String cvv = req.getParameter("cvv");
        String currentTime;

        if(cvv.equals("666")){
            orderService.updateOrder(order, "credit", false);

        }else{
            currentTime = InputValidator.formatLocalDateTimeNowToString();
            orderService.updateOrder(order, "credit", true);
        }

        InputValidator.createJsonFromObject(resp, orderService.getOrder(1));
    }

}