package com.sda.booking.commons;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public void sendEmail(String message, String mailAddress, String subject ) {

        final String username = "java2Iasi@gmail.com";
        final String password = "Java2Iasi2018";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress("java2Iasi@gmail.com"));
            mes.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailAddress)
            );
            mes.setSubject(subject);
            mes.setText(message);

            Transport.send(mes);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
