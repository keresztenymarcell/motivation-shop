package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.DatabaseManager;
import com.codecool.shop.util.PropertyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class Initializer implements ServletContextListener {

    private static String connectionType;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties p = PropertyProvider.getPropertiesFromConnectionConfig();
        Logger logger = LoggerFactory.getLogger(Initializer.class);


        logger.info("First info");

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
