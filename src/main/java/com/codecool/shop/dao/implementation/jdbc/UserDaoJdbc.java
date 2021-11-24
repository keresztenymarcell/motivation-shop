package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc extends DatabaseConnection implements UserDao {

    private static UserDaoJdbc instance;

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User find(int id) {
//        try (Connection conn = dataSource.getConnection()) {
//            String sql = "SELECT email, name, password, order_id FROM users WHERE id=?";
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setInt(1, id);
//            ResultSet rs = st.executeQuery();
//            if (!rs.next()) {
//                return null;
//            }
//            User user = new User(rs.getString(2));
//            user.setId(id);
//            return user;
//        } catch (SQLException e) {
//            throw new RuntimeException("Error while reading author with id: " + id, e);
//        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
