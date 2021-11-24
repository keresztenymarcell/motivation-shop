package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.LineItem;
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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Set;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class EditCartController extends HttpServlet {
    ShoppingCartService shoppingCartService = ServiceProvider.getShoppingCartService();

    public EditCartController(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ShoppingCart shoppingCart = shoppingCartService.getCartByUser(1);

        context.setVariable("shoppingCart", shoppingCart);
        context.setVariable("totalPrice", shoppingCart.getTotalPrice());
        context.setVariable("totalItems", shoppingCart.getTotalItems());

        engine.process("product/shopping_cart.html", context, resp.getWriter());
    }
}
