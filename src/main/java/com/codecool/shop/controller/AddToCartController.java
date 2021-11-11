package com.codecool.shop.controller;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.service.Service;
import com.codecool.shop.util.InputValidator;
import com.codecool.shop.util.ServiceProvider;

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
public class AddToCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceProvider.getService();


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
        String currentTime;

        User currentUser = service.getUser(1);

        if(!currentUser.hasOrder()){
            currentOrder = new Order(currentUser);
        }
        else{
            currentOrder = currentUser.getOrder();
        }
        currentOrder.addItemToCart(lineItem);
        currentTime = InputValidator.formatLocalDateToString(LocalDateTime.now());
        currentOrder.setOrderTime(currentTime);

        PaymentCredit.createJsonFromObject(resp, currentOrder);
    }

}
