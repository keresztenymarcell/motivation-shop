package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    Order mockOrder;
    User mockUser;
    ShippingDetails mockShippingDetails;

    OrderDao mockOrderDao;
    UserDao mockUserDao;
    ShippingDetailsDao mockShippingDetailsDao;

    List<Order> mockOrders;
    List<ShippingDetails> mockShippingDetailsContainer;

    OrderService orderService;

    @BeforeEach
    void setUp() {
        mockOrder = mock(Order.class);
        mockUser = mock(User.class);
        mockShippingDetails = mock(ShippingDetails.class);

        mockOrderDao = mock(OrderDao.class);
        mockUserDao = mock(UserDao.class);
        mockShippingDetailsDao = mock(ShippingDetailsDao.class);

        mockOrders = new LinkedList<>(Arrays.asList(
                mock(Order.class),
                mock(Order.class),
                mock(Order.class)));

        mockShippingDetailsContainer = new LinkedList<>(Arrays.asList(
                mock(ShippingDetails.class),
                mock(ShippingDetails.class),
                mock(ShippingDetails.class)));

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