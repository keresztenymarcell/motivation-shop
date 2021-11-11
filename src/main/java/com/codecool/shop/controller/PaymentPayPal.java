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
import com.codecool.shop.model.User;
import com.codecool.shop.service.Service;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "paymentPayPal", urlPatterns = {"/payment/paypal"}, loadOnStartup = 1)
public class PaymentPayPal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        UserDao userDataStore = UserDaoMem.getInstance();
        Service service = new Service(productDataStore,productCategoryDataStore,supplierDataStore, userDataStore);

        User user = service.getUser(1);
        Order orderWithPaymentDetails = user.getOrder();

        String userName = req.getParameter("paypal-user");
        if(userName.equals("evil")){
            orderWithPaymentDetails.setSuccessPayment(false);

        }else{
            orderWithPaymentDetails.setOrderTime(LocalDateTime.now().toString());
            orderWithPaymentDetails.setPaymentMethod("paypal");
            orderWithPaymentDetails.setSuccessPayment(true);
        }

        String responseString = new Gson().toJson(orderWithPaymentDetails);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
    }
}
