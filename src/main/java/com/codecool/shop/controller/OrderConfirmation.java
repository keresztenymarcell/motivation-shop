package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ShoppingCartService;
import com.codecool.shop.util.EmailSender;
import com.codecool.shop.util.ServiceProvider;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderConfirmation", urlPatterns = {"/confirmation"})
public class OrderConfirmation extends HttpServlet {

    OrderService orderService = ServiceProvider.getOrderService();
    ShoppingCartService shoppingCartService = ServiceProvider.getShoppingCartService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        Order order = orderService.getOrder(1);
        ShoppingCart cart = shoppingCartService.getCartByUser(1);

        context.setVariable("cartItems", cart.getTotalItems());
        context.setVariable("cart", cart);
        context.setVariable("order", order);

        orderService.getShippingDetails(1).saveToJson();

        shoppingCartService.emptyCart(1);

        try {
            EmailSender.sendEmail(order, engine.process("confirmation.html", context));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        engine.process("confirmation.html", context, response.getWriter());
    }
}
