package com.codecool.shop.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyProvider {

    public static Properties getPropertiesFromConnectionConfig() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/connection.properties");
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties p = new Properties();
        try {
            p.load(fis);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return p;
    }
}
