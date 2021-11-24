package com.codecool.shop.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    public static boolean emailInputCorrectFormat(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static int checkIntInput(String stringInput) {
        try {
            int input = Integer.parseInt(stringInput);
            if (input < 1) {
                return  1;
            } else {
                return input;
            }
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public static String formatLocalDateTimeNowToString(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(formatter);
    }

    public static void createJsonFromObject(HttpServletResponse resp, Object object) throws IOException {
        String responseString = new Gson().toJson(object);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
    }
}
