package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        ProductService service = null;
        try {
            service = ServiceProvider.getService();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = service.getUser(1);
        Order order = user.getCart();

        context.setVariable("userId", order.getTotalItems());
        context.setVariable("order", order);

        order.getShippingDetails().saveToJson();
        try {
            EmailSender.sendEmail(order, engine.process("confirmation.html", context));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        user.setCart(new Order(user));


        engine.process("confirmation.html", context, response.getWriter());
    }
}
