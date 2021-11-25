package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    Order mockOrder;
    User mockUser;
    ShippingDetails mockShippingDetails;

    OrderDao mockOrderDao;
    UserDao mockUserDao;
    ShippingDetailsDao mockShippingDetailsDao;

    OrderService orderService;

    @BeforeEach
    void setUp() {
        mockOrder = mock(Order.class);
        mockUser = mock(User.class);
        mockShippingDetails = mock(ShippingDetails.class);

        mockOrderDao = mock(OrderDao.class);
        mockUserDao = mock(UserDao.class);
        mockShippingDetailsDao = mock(ShippingDetailsDao.class);

        orderService = new OrderService(mockOrderDao, mockShippingDetailsDao, mockUserDao);

    }

    @Test
    void getOrderByUserId_ReturnsUserOrder() {
        when(mockOrderDao.find(1)).thenReturn(mockOrder);
        assertEquals(mockOrder, orderService.getOrder(1));
    }

    @Test
    void getShippingDetailsByUserId_ReturnsUserShippingDetails() {
        when(mockShippingDetailsDao.find(1)).thenReturn(mockShippingDetails);
        assertEquals(mockShippingDetails, orderService.getShippingDetails(1));
    }
}