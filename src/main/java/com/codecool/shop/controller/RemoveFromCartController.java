package com.codecool.shop.controller;

import com.codecool.shop.model.LineItem;
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

@WebServlet(name = "removeFromCartController", urlPatterns = {"/api/remove-from-cart"}, loadOnStartup = 1)
public class RemoveFromCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Service service = ServiceProvider.getService();

        int id = InputValidator.checkIntInput(req.getParameter("id"));

        Product product  = service.getProduct(id);
        LineItem lineItem = new LineItem(product);

        User currentUser = service.getUser(1);
        currentUser.getOrder().removeItemFromCart(lineItem);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{}");
        out.flush();
    }

}





