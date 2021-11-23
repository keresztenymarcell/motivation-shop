package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
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
