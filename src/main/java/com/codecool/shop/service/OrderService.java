package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.*;
import com.codecool.shop.util.InputValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderService {
    private OrderDao orderDao;//done
    private ShippingDetailsDao shippingDetailsDao;//done
    private UserDao userDao;//done

    public OrderService(OrderDao orderDao, ShippingDetailsDao shippingDetailsDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.shippingDetailsDao = shippingDetailsDao;
        this.userDao = userDao;
    }

    public void createOrder(int userId){
        User user = userDao.find(userId);//done
        ShippingDetails sd = shippingDetailsDao.find(userId);// done
        Order order = new Order(user, sd);
        orderDao.add(order);//done
    }

    public Order getOrder(int userId){
        return orderDao.find(userId);
    }

    public void saveShippingDetails(int userId, String name,String email,String country,String zipcode,String address){
        String orderTime = InputValidator.formatLocalDateTimeNowToString();
        ShippingDetails data = new ShippingDetails(1, name, email, country, zipcode, address, orderTime);
        shippingDetailsDao.add(data);
    }

    public ShippingDetails getShippingDetails(int userId){
        return shippingDetailsDao.find(userId);
    }


    public void updateOrder(Order order, String credit, boolean success) {
        order.setPaymentMethod(credit);
        order.setSuccessPayment(success);

        orderDao.update(order); //done

    }
}
