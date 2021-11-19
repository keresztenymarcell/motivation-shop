package com.codecool.shop.util;

public class OrderInformationInputChecker {
    public static String getErrorMessageForInputs(String name, String email, String country, String address) {
        String messageStart = "Please fill your ";
        String errorMessage = messageStart;

        if (name.equals("")) {
            errorMessage += "Name ";
        }

        if (!InputValidator.emailInputCorrectFormat(email)) {
            errorMessage += "Email ";
        }

        if (country.equals("")) {
            errorMessage += "Country ";
        }

        if (address.equals("")) {
            errorMessage += "Address";
        }

        return errorMessage.equals(messageStart) ? null : errorMessage;
    }
}
