package com.codecool.shop.controller;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
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

@WebServlet(name = "paymentPayPal", urlPatterns = {"/payment/paypal"}, loadOnStartup = 1)
public class PaymentPayPal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService service = null;
        try {
            service = ServiceProvider.getService();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = service.getUser(1);
        Order orderWithPaymentDetails = user.getCart();
        String currentTime;

        String userName = req.getParameter("paypal-user");
        if(userName.equals("evil")){
            orderWithPaymentDetails.setSuccessPayment(false);

        }else{
            currentTime = InputValidator.formatLocalDateTimeNowToString();
            orderWithPaymentDetails.setOrderTime(currentTime);
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
