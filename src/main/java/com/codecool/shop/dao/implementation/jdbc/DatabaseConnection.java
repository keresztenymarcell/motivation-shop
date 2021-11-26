package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.util.PropertyProvider;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    protected DataSource dataSource = connect();

    public DataSource connect(){
        try{
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            Properties p = PropertyProvider.getPropertiesFromConnectionConfig();
            String dbName = (String) p.get ("database");
            String user = (String) p.get ("user");
            String password = (String) p.get ("password");
            dataSource.setDatabaseName(dbName);
            dataSource.setUser(user);
            dataSource.setPassword(password);
            //System.out.println("Trying to connect");
            dataSource.getConnection().close();
            //System.out.println("Connection ok.");

            return dataSource;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
