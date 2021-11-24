package com.codecool.shop.controller;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.util.InputValidator;
import com.codecool.shop.util.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "cartController", urlPatterns = {"/api/add-to-cart"}, loadOnStartup = 1)
public class AddToCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService service = null;
        try {
            service = ServiceProvider.getService();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int id = InputValidator.checkIntInput(req.getParameter("id"));

        //service.addItemToCart(id);


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

        currentTime = InputValidator.formatLocalDateTimeNowToString();
        currentOrder.setOrderTime(currentTime);

        OrderService orderService = new OrderService(currentOrder);
        orderService.addCartItem(lineItem);


        PaymentCredit.createJsonFromObject(resp, currentOrder);
    }

}
