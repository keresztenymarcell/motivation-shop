package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderService {

    private Order order;

    public OrderService(Order order) {
        this.order = order;
    }

    public Set<LineItem> getAllCartItems() {
        return order.getCart();
    }

    public BigDecimal getTotalPrice() {
        return order.getOrderTotalValue();
    }

    public void addCartItem(LineItem item) {
        order.addItemToCart(item);
    }

    public void removeCartItem(LineItem item) {
        order.removeItemFromCart(item);
    }

    public Order getOrder() {
        return order;
    }


    public int getOrderID() {
        return getOrderID();
    }

    public void clearCart() {
        order.emptyCart();
    }

}
