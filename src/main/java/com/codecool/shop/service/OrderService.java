package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderService {
    private OrderDao orderDao;
    private ShippingDetailsDao shippingDetailsDao;
    private UserDao userDao;

    public OrderService(OrderDao orderDao, ShippingDetailsDao shippingDetailsDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.shippingDetailsDao = shippingDetailsDao;
        this.userDao = userDao;
    }

    public void createOrder(int userId){
        User user = userDao.find(userId);
        ShippingDetails sd = shippingDetailsDao.find(userId);
        Order order = new Order(user, sd);
        orderDao.add(order);
    }

    public Order getOrder(int userId){
        return orderDao.find(userId);
    }

    public void saveShippingDetails(int userId, String name,String email,String country,String zipcode,String address){
        ShippingDetails data = new ShippingDetails(1, name, email, country, zipcode, address);
        shippingDetailsDao.add(data);
    }

    public ShippingDetails getShippingDetails(int userId){
        return shippingDetailsDao.find(userId);
    }


    public void updateOrder(Order order, String credit, boolean success) {
        order.setPaymentMethod(credit);
        order.setSuccessPayment(success);

        orderDao.update(order);

    }
}
