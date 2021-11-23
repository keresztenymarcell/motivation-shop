package com.codecool.shop.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public String className;

    public Logger(String className) {
        this.className = className;
    }

    public void info(String message) {

        System.out.printf("%s [INFO] %s : %s\n", getDate(), className, message);

    }

    public void trace(String message) {

        System.out.printf("%s [TRACE] %s : %s\n", getDate(), className, message);

    }

    public void debug(String message) {

        System.out.printf("%s [DEBUG] %s : %s\n", getDate(), className, message);

    }

    public void warn(String message) {
        System.out.printf("%s [WARNING] %s : %s\n", getDate(), className, message);
    }

    public void error(String message) {
        System.out.printf("%s [ERROR] %s : %s\n", getDate(), className, message);
    }

    private String getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd hh:mm:ss");
        return now.format(formatter);
    }
}
