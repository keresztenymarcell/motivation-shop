package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderService {
    private OrderDao orderDao;
    private ShippingDetailsDao shippingDetailsDao;


    public OrderService(OrderDao orderDao, ShippingDetailsDao shippingDetailsDao) {
        this.orderDao = orderDao;
        this.shippingDetailsDao = shippingDetailsDao;;
    }




}
