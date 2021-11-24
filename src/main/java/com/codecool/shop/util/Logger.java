package com.codecool.shop.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public String className;

    public Logger(String className) {
        this.className = className;
    }

    public void info(String message) {
        String log = String.format("%s [INFO] %s : %s\n", getDate(), className, message);
        doStuff(log);

    }

    public void trace(String message) {
        String log = String.format("%s [TRACE] %s : %s\n", getDate(), className, message);
        doStuff(log);

    }

    public void debug(String message, int temperature, int oldTemp) {
        String log = String.format("%s [DEBUG] %s : %s\n", getDate(), className, message);
        doStuff(log);

    }

    public void warn(String message) {
        String log = String.format("%s [WARNING] %s : %s\n", getDate(), className, message);
        doStuff(log);
    }

    public void error(String message) {
        String log = String.format("%s [ERROR] %s : %s\n", getDate(), className, message);
        doStuff(log);
    }

    private void doStuff(String log) {
        System.out.print(log);
        writeToFile(log);
    }

    private String getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    private void writeToFile(String log) {
        try {
            File file = new File("logs");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(log);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
