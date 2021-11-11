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


        String isSuccess;

        String userName = req.getParameter("user");
        if(userName.equals("evil")){
            isSuccess = "false";
            System.out.println("false");
            user.getOrder().setSuccessPayment(false);

        }else{
            isSuccess = "true";
            System.out.println("true");
            user.getOrder().setPaymentMethod("paypal");
            user.getOrder().setSuccessPayment(true);
        }

        String responseString = new Gson().toJson(isSuccess);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
    }
}
