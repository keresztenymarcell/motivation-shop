package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.Service;
import com.codecool.shop.util.ServiceProvider;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class EditCartController extends HttpServlet {
    Service service = ServiceProvider.getService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = service.getUser(1);
        Set<LineItem> shoppingCart = user.getOrder().getCart();
        BigDecimal totalPrice = user.getOrder().getOrderTotalValue();
        int totalItems = user.getOrder().getTotalItems();
        context.setVariable("shoppingCart", shoppingCart);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("totalItems", totalItems);

        engine.process("product/shopping_cart.html", context, resp.getWriter());
    }

}
