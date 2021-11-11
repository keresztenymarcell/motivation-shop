package com.codecool.shop.controller;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.codecool.shop.service.Service;
import com.codecool.shop.util.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Set;

@WebServlet(name = "orderApi", urlPatterns = {"/api/order"}, loadOnStartup = 1)
public class OrderApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceProvider.getService();

        User user = service.getUser(1);
        Order currentOrder = user.getOrder();
        String productString = new Gson().toJson(currentOrder);
        currentOrder.setOrderTime(LocalDateTime.now().toString());
        /*Set<LineItem> currentOrderCart = currentOrder.getCart();
        String productString = new Gson().toJson(currentOrderCart);*/

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(productString);
        out.flush();

    }
}