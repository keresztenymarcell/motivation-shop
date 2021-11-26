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
        Properties p = PropertyProvider.getPropertiesFromConnectionConfig();
        String dao= (String) p.get ("dao");
        connectionType = dao;



        if (dao.equals("memory")) {
            logger.info("Server running with In-memory DAO");
            MemInitializer memInitializer = new MemInitializer();
            memInitializer.initializeMem();

        }
        else if (dao.equals("jdbc")) {
            logger.info("Server running with JDBC DAO");
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
