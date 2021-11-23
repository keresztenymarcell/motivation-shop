package com.codecool.shop.controller;

import com.codecool.shop.service.Service;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.util.ServiceProvider;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceProvider.getService();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", service.getAllCategories());
        context.setVariable("suppliers", service.getAllSuppliers());
        context.setVariable("products", service.getAllProducts());

        engine.process("product/index.html", context, resp.getWriter());
    }

}
