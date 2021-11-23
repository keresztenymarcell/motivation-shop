package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class LineItemDaoJdbc extends DatabaseConnection implements LineItemDao {

    DataSource dataSource;

    public LineItemDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(LineItem item) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO line_items(quantity, order_id, product_id) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, item.getQuantity());
            //how to get order id
            st.setInt(2, 1);
            st.setInt(3, item.getProductId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LineItem find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getAll(int orderId) {
        return null;
    }
}
