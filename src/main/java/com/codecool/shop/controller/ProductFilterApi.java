package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.Service;
import com.codecool.shop.util.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "productFilterApi", urlPatterns = {"/api/filter"}, loadOnStartup = 1)
public class ProductFilterApi extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Service service = ServiceProvider.getService();

        String name = req.getParameter("name");
        int id;

        try {
            id = Integer.parseInt(req.getParameter("id"));
            if (id < 1) {
                id = 1;
            }
        } catch (NumberFormatException e) {
            id = 1;
        }

        List<Product> productList = new ArrayList<>();

        if (name.equals("category")) {
            productList = service.getProductsForCategory(id);
        } else if (name.equals("supplier")) {
            productList = service.getProductsForSupplier(id);
        }

        String productString = new Gson().toJson(productList);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(productString);
        out.flush();
    }
}
