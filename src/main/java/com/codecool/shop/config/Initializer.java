package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.DatabaseManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class Initializer implements ServletContextListener {



    private static String connectionType;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileInputStream fis= null;
        try {
            fis = new FileInputStream("src/main/resources/connection.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties p=new Properties ();
        try {
            p.load (fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url= (String) p.get ("url");
        String database= (String) p.get ("database");
        String password= (String) p.get ("password");
        String dao= (String) p.get ("dao");

        connectionType = dao;

        if (dao.equals("memory")) {
            System.out.println("I run Mem");
            MemInitializer memInitializer = new MemInitializer();
            memInitializer.initializeMem();

        }
        else if (dao.equals("jdbc")) {
            System.out.println("I run JDBC");
            DatabaseManager dbmanager = new DatabaseManager();
            try {
                dbmanager.setup();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getConnectionType() {
        return connectionType;
    }

}
