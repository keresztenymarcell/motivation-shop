package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

public class UserService {

    UserDao userDataStore;

    public UserService(UserDao userDataStore) {
        this.userDataStore = userDataStore;
    }

    public User getUser(int id){
        return userDataStore.find(id);
    }
}
