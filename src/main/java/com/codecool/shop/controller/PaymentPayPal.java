package com.codecool.shop.controller;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
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

@WebServlet(name = "paymentPayPal", urlPatterns = {"/payment/paypal"}, loadOnStartup = 1)
public class PaymentPayPal extends HttpServlet {

    OrderService orderService = ServiceProvider.getOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = orderService.getOrder(1);
        String currentTime;

        String userName = req.getParameter("paypal-user");
        if(userName.equals("evil")){
            orderService.updateOrder(order, "paypal", false);

        }else{
            currentTime = InputValidator.formatLocalDateTimeNowToString();
            orderService.updateOrder(order, "paypal", true);
        }
        order = orderService.getOrder(1);
        InputValidator.createJsonFromObject(resp, order);

    }
}
