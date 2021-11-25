package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.util.InputValidator;
import com.codecool.shop.util.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "productFilterApi", urlPatterns = {"/api/filter"}, loadOnStartup = 1)
public class ProductFilterApi extends HttpServlet {

    ProductService productService = ServiceProvider.getProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int id = InputValidator.checkIntInput(req.getParameter("id"));

        List<Product> productList = new ArrayList<>();

        if (name.equals("category")) {
            productList = productService.getProductsForCategory(id);
        } else if (name.equals("supplier")) {
            productList = productService.getProductsForSupplier(id);
        }

        InputValidator.createJsonFromObject(resp,productList);
    }
}
