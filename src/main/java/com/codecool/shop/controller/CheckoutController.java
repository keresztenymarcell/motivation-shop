package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.Service;
import com.codecool.shop.util.OrderInformationInputChecker;
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

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        UserDao userDaoStore = UserDaoMem.getInstance();
        Service service = new Service(productDataStore,productCategoryDataStore,supplierDataStore, userDaoStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String zipcode = req.getParameter("zipcode");
        String address = req.getParameter("address");

        String errorMessage = OrderInformationInputChecker.getErrorMessageForInputs(name, email, country, address);

        if (errorMessage != null) {
            context.setVariable("message", errorMessage);
            engine.process("product/checkout.html", context, resp.getWriter());
        } else {
            Order currentOrder = service.getUser(1).getOrder();
            currentOrder.setOrderName(name);
            currentOrder.setEmail(email);
            currentOrder.setCountry(country);
            currentOrder.setZipcode(zipcode);
            currentOrder.setAddress(address);

            System.out.println(currentOrder);
            // here we can connect Betty's servlet with the payment
            resp.sendRedirect("/");
        }

    }
}
