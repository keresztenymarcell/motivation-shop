package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.DatabaseManager;

import com.codecool.shop.util.PropertyProvider;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        Logger logger = Logger.getLogger(Initializer.class);
        logger.info("First info");
        logger.info("second info");
        logger.warn("Dafuq");

        int temperature = 50;
        int oldTemp = 30;

        logger.debug("Temperature set to!!!");

        Properties p = PropertyProvider.getPropertiesFromConnectionConfig();


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
