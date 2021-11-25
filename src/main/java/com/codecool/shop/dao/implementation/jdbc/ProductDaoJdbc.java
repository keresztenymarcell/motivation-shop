package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc extends DatabaseConnection implements ProductDao {
    private static ProductDaoJdbc instance;
    private ProductCategoryDaoJdbc productCategoryDaoJDBC = ProductCategoryDaoJdbc.getInstance();
    private SupplierDaoJdbc supplierDaoJDBC = SupplierDaoJdbc.getInstance();

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        try {
            Connection conn = dataSource.getConnection();
            String query = String.format("insert into products values (%d %s %s %f %s %d %d)",
                    product.getId(), product.getName(), product.getDescription(), product.getDefaultPrice(),
                    product.getDefaultCurrency().getCurrencyCode(), product.getProductCategory().getId(),
                    product.getSupplier().getId());
            PreparedStatement statement = conn.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description,default_price, default_currency, category_id, supplier_id FROM products WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Product product = new Product(rs.getString(2),
                    rs.getBigDecimal(4),
                    rs.getString(5),
                    rs.getString(3),
                    productCategoryDaoJDBC.find(rs.getInt(6)),
                    supplierDaoJDBC.find(rs.getInt(7)));
            product.setId(rs.getInt(1));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading author with id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()){
            String query = String.format("delete from products where id = %d", id);
            PreparedStatement statement = conn.prepareStatement(query);
            statement.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            // FIRST STEP - read book_id, author_id and title
            String sql = "SELECT id, name, description, default_price, default_currency, category_id, supplier_id FROM products";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getString(2),
                        rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getString(3),
                        productCategoryDaoJDBC.find(rs.getInt(6)),
                        supplierDaoJDBC.find(rs.getInt(7)));
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description,default_price, default_currency, category_id, supplier_id FROM products WHERE supplier_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, supplier.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(2),
                        rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getString(3),
                        productCategoryDaoJDBC.find(rs.getInt(6)),
                        supplierDaoJDBC.find(rs.getInt(7)));
                product.setId(rs.getInt(1));
                result.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products: " + e);
        }
        return result;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description,default_price, default_currency, category_id, supplier_id FROM products WHERE category_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(2),
                        rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getString(3),
                        productCategoryDaoJDBC.find(rs.getInt(6)),
                        supplierDaoJDBC.find(rs.getInt(7)));
                product.setId(rs.getInt(1));
                result.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products: " + e);
        }
        return result;
    }
}
