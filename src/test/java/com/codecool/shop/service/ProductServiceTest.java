package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class xProductServiceTest {

    Product mockProduct;
    ProductCategory mockProductCategory;
    Supplier mockSupplier;
    User mockUser;

    SupplierDao mockSupplierDao;
    UserDao mockUserDao;
    ProductDao mockProductDao;
    ProductCategoryDao mockProductCategoryDao;

    List<Product> mockProducts;
    List<ProductCategory> mockCategories;
    List<Supplier> mockSuppliers;

    ProductService productService;

    @BeforeEach
    void setUp() {
        mockProduct = mock(Product.class);
        mockProductCategory = mock(ProductCategory.class);
        mockSupplier = mock(Supplier.class);
        mockUser = mock(User.class);

        mockProductDao = mock(ProductDao.class);
        mockProductCategoryDao = mock(ProductCategoryDao.class);
        mockSupplierDao = mock(SupplierDao.class);
        mockUserDao = mock(UserDao.class);

        mockProducts = new LinkedList<>(
                Arrays.asList(
                        mock(Product.class),
                        mock(Product.class),
                        mock(Product.class)));

        mockCategories = Arrays.asList(
                mock(ProductCategory.class),
                mock(ProductCategory.class),
                mock(ProductCategory.class));

        mockSuppliers = Arrays.asList(
                mock(Supplier.class),
                mock(Supplier.class),
                mock(Supplier.class));

        productService = new ProductService(mockProductDao, mockProductCategoryDao,mockSupplierDao, mockUserDao);
    }



    @Test
    public void getProductsForCategory_ID1_ReturnsTrue() {
        when(mockProductCategoryDao.find(1)).thenReturn(mockProductCategory);
        when(mockProductDao.getBy(mockProductCategoryDao.find(1))).thenReturn(mockProducts);

        assertEquals(mockProducts, productService.getProductsForCategory(1));
    }

    @Test
    public void getProductsForSupplier_ID1_ReturnsTrue() {
        when(mockSupplierDao.find(1)).thenReturn(mockSupplier);
        when(mockProductDao.getBy(mockSupplierDao.find(1))).thenReturn(mockProducts);

        assertEquals(mockProducts, productService.getProductsForSupplier(1));
    }

    @Test
    void getALlProducts_ReturnsAllProducts() {
        when(mockProductDao.getAll()).thenReturn(mockProducts);
        assertEquals(mockProducts, productService.getAllProducts());
    }

    @Test
    void getAllSuppliers_ReturnsAllSuppliers() {
        when(mockSupplierDao.getAll()).thenReturn(mockSuppliers);
        assertEquals(mockSuppliers, productService.getAllSuppliers());
    }

    @Test
    void getAllProductCategories_ReturnsAllCategories() {
        when(mockProductCategoryDao.getAll()).thenReturn(mockCategories);
        assertEquals(mockCategories, productService.getAllCategories());
    }

}