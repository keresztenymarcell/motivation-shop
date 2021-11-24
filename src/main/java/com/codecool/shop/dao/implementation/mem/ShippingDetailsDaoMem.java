package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.ShippingDetailsDao;
import com.codecool.shop.model.ShippingDetails;

import java.util.ArrayList;
import java.util.List;

public class ShippingDetailsDaoMem implements ShippingDetailsDao {

    private List<ShippingDetails> data = new ArrayList<>();
    private static ShippingDetailsDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ShippingDetailsDaoMem() {
    }

    public static ShippingDetailsDaoMem getInstance() {
        if (instance == null) {
            instance = new ShippingDetailsDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ShippingDetails item) {

    }

    @Override
    public ShippingDetails find(int id) {
        return null;
    }

    @Override
    public void update(ShippingDetails shippingDetails) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ShippingDetails> getAll(int orderId) {
        return null;
    }
}
