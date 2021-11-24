package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ShoppingCartService;
import com.codecool.shop.util.ServiceProvider;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "paymentController", urlPatterns = {"/payment-page"}, loadOnStartup = 1)
public class PaymentController extends HttpServlet {

    ShoppingCartService shoppingCartService = ServiceProvider.getShoppingCartService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        ShoppingCart shoppingCart = shoppingCartService.getCartByUser(1);

        context.setVariable("order", shoppingCart);
        engine.process("product/payment.html", context, resp.getWriter());
    }
}
