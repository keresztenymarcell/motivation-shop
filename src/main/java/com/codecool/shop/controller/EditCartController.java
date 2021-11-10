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
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class EditCartController extends HttpServlet {
    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    UserDao userDaoStore = UserDaoMem.getInstance();
    Service service = new Service(productDataStore,productCategoryDataStore,supplierDataStore, userDaoStore);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = service.getUser(1);
        Set<LineItem> shoppingCart = user.getOrder().getCart();
        BigDecimal totalPrice = user.getOrder().getOrderTotalValue();
        int totalItems = user.getOrder().getTotalItems();
        context.setVariable("shoppingCart", shoppingCart);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("totalItems", totalItems);


        System.out.println(shoppingCart);

        engine.process("product/shopping_cart.html", context, resp.getWriter());
    }

}
