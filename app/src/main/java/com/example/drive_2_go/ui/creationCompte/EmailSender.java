package com.example.drive_2_go.ui.creationCompte;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void sendEmail(String to, String subject, String messageBody) {

        final String fromEmail = "bassouhind76@gmail.com";
        final String password = "kyzw zlqv zdox szzk"; // mot de passe application (Google)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(messageBody);

            Transport.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
