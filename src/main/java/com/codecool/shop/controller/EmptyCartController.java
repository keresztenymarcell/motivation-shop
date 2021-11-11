package com.codecool.shop.controller;

import com.codecool.shop.model.User;
import com.codecool.shop.service.Service;
import com.codecool.shop.util.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/empty-cart"}, loadOnStartup = 1)
public class EmptyCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Service service = ServiceProvider.getService();
        User currentUser = service.getUser(1);
        currentUser.getOrder().emptyCart();

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{}");
        out.flush();

    }
}
