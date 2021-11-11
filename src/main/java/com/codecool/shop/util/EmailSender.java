package com.codecool.shop.util;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.google.gson.Gson;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(Order order, User user) throws MessagingException {
        Properties prop = new Properties();
        String d_email = "vinczeg1281@gmail.com";
        String d_host = "smtp.gmail.com";
        String d_port = "465";
        prop.put("mail.smtp.user", d_email);
        prop.put("mail.smtp.host", d_host);
        prop.put("mail.smtp.port", d_port);
        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", d_port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");


        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vinczeg1281@gmail.com", System.getenv("EMAIL_PASSWORD"));
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("vinczeg1281@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(order.getEmail()));
        message.setSubject("Order Confirmation");

        Gson gson = new Gson();
        String json = gson.toJson(order);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(json, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

}
