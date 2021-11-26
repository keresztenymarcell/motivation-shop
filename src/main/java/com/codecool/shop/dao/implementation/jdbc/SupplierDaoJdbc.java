package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private final Logger logger = Logger.getLogger(Initializer.class);
    private DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM suppliers WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Supplier supplier = new Supplier(rs.getString(2), rs.getString(3));
            supplier.setId(id);
            return supplier;
        } catch (SQLException e) {
            logger.error("SupplierDao find SQL exception!");
            throw new RuntimeException("Error while reading author with id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM suppliers";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getString(2),
                        rs.getString(3));
                supplier.setId(rs.getInt(1));
                result.add(supplier);
            }
        } catch (SQLException e) {
            logger.error("SupplierDao getAll SQL exception!");
            throw new RuntimeException("Error while reading all products: " + e);
        }
        return result;
    }
}
