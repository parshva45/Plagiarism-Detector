package edu.northeastern.cs5500.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Praveen Singh
 */
public class RegisterEmail {
    public static void sendEmail(String name, String email) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("spoiledtomatillos@gmail.com",
                                "SpoiledTomatillosSpoiledTomatillos");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("spoiledtomatillos@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,

                    InternetAddress.parse(email));
            message.setSubject("Spoiled Tomatillos");
            message.setText("Dear " + name + ":" +
                    "\n\n This is Spoiled Tomatillos! Thanks for using our products! :)" +
                    "\n\n - Team 34");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
