package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "checkoutController", urlPatterns = {"/checkout"}, loadOnStartup = 1)
public class CheckoutController extends HttpServlet {

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

        boolean x = false;
        if (name.equals("Gergő")) {
            x = true;
        }

        if (!x) {
            context.setVariable("message", "Bad job!");

        }
        engine.process("product/checkout.html", context, resp.getWriter());
    }
}
