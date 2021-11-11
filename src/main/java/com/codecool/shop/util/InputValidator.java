package com.codecool.shop.util;

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
}
