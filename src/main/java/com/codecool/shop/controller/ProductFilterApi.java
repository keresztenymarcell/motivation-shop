package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.Service;
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

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        UserDao userDaoStore = UserDaoMem.getInstance();
        Service service = new Service(productDataStore,productCategoryDataStore,supplierDataStore, userDaoStore);

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
