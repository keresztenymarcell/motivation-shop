package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ShippingDetails;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ShoppingCartService;
import com.codecool.shop.util.OrderInformationInputChecker;
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

@WebServlet(name = "checkoutController", urlPatterns = {"/checkout"}, loadOnStartup = 1)
public class CheckoutController extends HttpServlet {

    ProductService productService = ServiceProvider.getProductService();
    ShoppingCartService shoppingCartService = ServiceProvider.getShoppingCartService();
    OrderService orderService = ServiceProvider.getOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/checkout.html", context, resp.getWriter());



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String zipcode = req.getParameter("zipcode");
        String address = req.getParameter("address");

        orderService.saveShippingDetails(1, name, email, country, zipcode, address);
        String errorMessage = OrderInformationInputChecker.getErrorMessageForInputs(name, email, country, address);

        if (errorMessage != null) {
            context.setVariable("message", errorMessage);
            engine.process("product/checkout.html", context, resp.getWriter());
        } else {
            orderService.createOrder(1);

            // here we can connect Betty's servlet with the payment
            resp.sendRedirect("/payment-page");
        }

    }
}
