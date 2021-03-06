package com.codecool.shop.controller;

import com.codecool.shop.model.*;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ShoppingCartService;
import com.codecool.shop.service.UserService;
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
public class CartApi extends HttpServlet {

    ShoppingCartService shoppingCartservice = ServiceProvider.getShoppingCartService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = InputValidator.checkIntInput(req.getParameter("id"));
//        int quantity = InputValidator.checkIntInput(req.getParameter("quantity"));

        shoppingCartservice.addProductToCart(1, productId);
        InputValidator.createJsonFromObject(resp, shoppingCartservice.getCartByUser(1));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = InputValidator.checkIntInput(req.getParameter("id"));

        shoppingCartservice.removeProductFromCart(1, productId);

        InputValidator.createJsonFromObject(resp, shoppingCartservice.getCartByUser(1));
        //TODO javascript fetch to POST
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        shoppingCartservice.emptyCart(1);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{}");
        out.flush();
    }


}
